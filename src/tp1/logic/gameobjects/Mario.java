//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.logic.gameobjects;


import tp1.view.Messages;
import tp1.logic.*;



public class Mario extends MovingObject{

	private String icon;
	private boolean big = true;



	
	public Mario(GameWorld game, Position pos) {
		
		super(game, pos);
		this.icon = Messages.MARIO_RIGHT;
		setFalling(false);
		setDirx(1);
	}
	
	@Override
	public void switchIcon() {
		
		if(getDirx() == -1) {
			this.icon = Messages.MARIO_LEFT;
		}
		else if(getDirx() == 1) {
			this.icon = Messages.MARIO_RIGHT;
		}
	}

	@Override
	public String getIcon() {		
		return icon;		
	}
	
	@Override
	public boolean isInPosition(Position pos){
			
		boolean isInPos = this.pos.equals(pos);
		
		if (this.isBig()) {
//			isInPos = this.pos.equals(pos) || this.pos.equals(pos.translate(new Position(0, 1)));
			Position topPos = this.pos.translate(new Position(0, -1));
			isInPos = this.pos.equals(pos) || topPos.equals(pos);
		}
		return isInPos;
	}
	
	private boolean canMoveTo(Position target) {

	    if (!game.isInside(target) || !game.isEmpty(target)) {
	        return false;
	    }
	    
	    if (isBig()) {
	        Position topTarget = target.translate(new Position(0, -1)); //posicion de arriba
	        if (!game.isInside(topTarget) || !game.isEmpty(topTarget)) {
	            return false; 
	        }
	    }    
	    return true;
	}
	
	
	public boolean processAction(Iterable<Action> actions) {
		
		return true;
	}
	
	public void muere() {
		this.dead();
		game.marioMuere(); // avisa al Game
	}

	
	public boolean isBig() {
	    return big;
	}
	
	public void setBig(boolean value) {
	    this.big = value;
	}
		
	public void givePointsToGame(int pts) {
	    game.addPoints(pts);
	}
	
	
	public boolean interactWith(GameItem other) {
		
		boolean success = false;
	    boolean canInteract = other.isInPosition(this.pos);
	    if (canInteract) {	
	        success = other.receiveInteraction(this);         
	    }
	    return canInteract && success;
	}
	
	@Override
	public boolean receiveInteraction(Goomba goomba) {

	    if (this.isFalling()) {
	        goomba.dead();
	        givePointsToGame(100);
	        return true;
	    }

	    if (this.isBig()) {
	        goomba.dead();
	        givePointsToGame(100);
	        this.setBig(false);
	        return true;
	    }

	    this.muere();
	    return true;
	}

	@Override
	public boolean receiveInteraction(Land land) {
	    return false; // Mario pisa tierra, no se si asi esta bien, misma duda con el de goomba
	}

	@Override
	public boolean receiveInteraction(Exit_door exit_door) {		
	    game.marioExited();
	    return true;
	}

	@Override
	public boolean receiveInteraction(Mario mario) {
	    return false;
	}


	@Override
	public void update() {
//		controller.consumeActions();
//		update(Iterable<Action> actions);	
		
	    if (!isAlive()) return;
	    
	    Game g = (Game) game; //no se si se puede hacer de otra manera
	    
		boolean ActionExecute = processActions(g.actions());
	   
		if(!ActionExecute) {
			super.update();
			if(!isAlive()) {
				game.marioMuere();
			}

		}
		game.doInteraction(this);

	}

	private boolean processActions(Iterable<Action> actions) {
		
		
	    boolean moved = false;
	    
	    Position below = pos.translate(new Position(0, 1));
	    
	    if (game.isInside(below) && game.isSolid(below)) {
	    	
	        // Está en el suelo
	        setFalling(false);
	    }
	    else setFalling(true);
	    
//	    } else if (game.isInside(below) && !game.isSolid(below)) {
//	    	
//	        // Está en el aire → cae
//	        pos = below;
//	        setFalling(true);
//	        moved = true;
//	    }

	    int maxL = 0, maxR = 0, maxU = 0;
	    //realmente sea necesario hacer dos cont para el mov horizontal?
	    for (Action act : actions) {
	    	
	        Position next = pos.translateMario(act);

	        // Movimiento horizontal
	        if (act == Action.LEFT || act == Action.RIGHT || act == Action.STOP) {
	        	
	            if (act == Action.LEFT) {
	            	setDirx(-1);
	            	if(maxL > 3) continue;
	            	maxL++;     
	            }
	            else if (act == Action.RIGHT) { 
	            	setDirx(1);
	            	if(maxR > 3) continue;
	            	maxR++;
	            }
	            else if (act == Action.STOP) setDirx(0);


	            // Actualizar icono
	            icon = switch (getDirx()) {
	            
	                case 1 -> Messages.MARIO_RIGHT;
	                case -1 -> Messages.MARIO_LEFT;
	                default -> Messages.MARIO_STOP;
	                
	            };

	            if (canMoveTo(next)) {
	                pos = next;
	                moved = true;
	            }
	        }

	        if (act == Action.UP) {
	        		           	            	
                if (game.isInside(next) && game.isEmpty(next)) {
                	if(canMoveTo(next)) {              		
                		if(maxU > 3) continue;
                		maxU++;
                		pos = next;
                		setFalling(true);
                		moved = true;                		               		
	                	}
	                }	            
	        }

	        // Caída rápida
	        if (act == Action.DOWN) {

	            if (isFalling()    /*game.isInside(below) && !game.isSolid(below)*/) {
	                while (game.isInside(below) && !game.isSolid(below)) {
	                    pos = below;
	                    below = pos.translateMario(act);
	                    if (!game.isInside(below)) {
	                        muere();
	                        return false;
	                    }
	                }
	                moved = true;
	                setFalling(false);	              
	            } else {
	                setDirx(0);
	                icon = Messages.MARIO_STOP;
	            }	        	
	        }	        	      
	    }
	    return moved;
	}
}
