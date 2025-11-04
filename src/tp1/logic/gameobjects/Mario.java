//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.logic.gameobjects;


import tp1.view.Messages;
import tp1.logic.*;



public class Mario extends MovingObject{

	private Position pos;
	private Game game;
//	private boolean vivo = true;
	private String icon;
	private boolean big = false;
	private int jumpcont = 0;



	
	public Mario(Game game, Position pos) {
		
		super(game, pos);
		this.icon = Messages.MARIO_RIGHT;
		setFalling(false);
		setDirx(1);
	}
	

	
	public String getIcon() {
		
		return icon;
		
	}
	
	@Override
	public boolean isInPosition(Position pos){
			
		boolean isInPos = this.pos.equals(pos);
		
		if (this.isBig()) {
			isInPos = this.pos.equals(pos) || this.pos.equals(pos.translate(new Position(0, 1)));
			
	}
		return isInPos;
	}
		
	
//	public boolean Esta_vivo() {
//		
//        return vivo;
//    
//	}
	
	
//	public void update(Iterable<Action> actions) {
//		
//		
//	    boolean moved = false;
//
//	    if (!isAlive()) return;
//	    
//	    Position below = pos.translate(new Position(0, 1));
//	    
//	    if (game.isInside(below) && game.isSolid(below)) {
//	    	
//	        // Está en el suelo
//	        setFalling(false);
//	        jumpcont = 0;
//	        
//	    } else if (game.isInside(below) && !game.isSolid(below)) {
//	    	
//	        // Está en el aire → cae
//	        pos = below;
//	        setFalling(true);
//	    }
//
//	    for (Action act : actions) {
//	    	
//	        Position next = pos.translateMario(act);
//
//	        // Movimiento horizontal
//	        if (act == Action.LEFT || act == Action.RIGHT || act == Action.STOP) {
//	        	
//	            if (act == Action.LEFT) setDirx(-1);
//	            else if (act == Action.RIGHT) setDirx(1);
//	            else if (act == Action.STOP) setDirx(0);
//
//	            // Actualizar icono
//	            icon = switch (getDirx()) {
//	            
//	                case 1 -> Messages.MARIO_RIGHT;
//	                case -1 -> Messages.MARIO_LEFT;
//	                default -> Messages.MARIO_STOP;
//	                
//	            };
//
//	            if (game.isInside(next) && game.isEmpty(next)) {
//	                pos = next;
//	                moved = true;
//	            }
//	        }
//
//	        // Salto (doble permitido)
//	        if (act == Action.UP) {
//	        	
//	            if (jumpcont < 2) {
//	            	
//	                if (game.isInside(next) && game.isEmpty(next)) {
//	                	
//	                    pos = next;
//	                    setFalling(true);
//	                    jumpcont++; // solo incrementa si realmente sube
//	                }
//	            }
//	            moved = true;
//	        }
//
//	        // Caída rápida
//	        if (act == Action.DOWN) {
////	            Position below = pos.translateMario(act);
//	            if (game.isInside(below) && !game.isSolid(below)) {
//	                while (game.isInside(below) && !game.isSolid(below)) {
//	                    pos = below;
//	                    below = pos.translateMario(act);
//	                    if (!game.isInside(below)) {
//	                        muere();
//	                        return;
//	                    }
//	                }
//	                moved = true;
//	            } else {
//	                setDirx(0);
//	                icon = Messages.MARIO_STOP;
//	            }
//	        }
//	        
//	    }
//
//
//	    // Movimiento horizontal automático solo si no se movió
//	    if (!moved) {
//	        Position next = pos.translate(new Position(getDirx(), 0));
//	        if (game.isInside(next) && game.isEmpty(next)) {
//	            pos = next;
//	        }
//	        else
//	        	setDirx(getDirx() * -1);
//	    }
//	}

	public boolean processAction(Iterable<Action> actions) {
		
		return true;
	}
	
	public void muere() {
		this.dead();
		game.marioMuere(); // avisa al Game
	}

//	public void addAction(Action act) {
//	    actions.add(act);
//		
//	}
	//Hay que quitar el get position
//	public boolean interactWith(Exit_door other) {
//		
////	    if (this.pos.equals(other.getPosition())) {
//	    if (other.isInPos(this.pos)) {
//	        game.marioExited(); // avisa al Game de que ha llegado
//	        return true;
//	    }
//	    return false;
//	}
//
//	public boolean interactWith(Goomba other) {
//		
////	    if (!this.pos.equals(other.getPosition())) return false;
//		if (!other.isInPos(this.pos)) return false;
//	    if (isFalling()) { // método que indica si Mario está cayendo
//	        other.receiveInteraction(this);
//	        return true;
//	    }
//
//	    if (this.isBig()) {	
//	        other.receiveInteraction(this);
//	        this.setBig(false);
//	        return true;
//	    }   
//	     else {
//	    	 this.muere();
//	    	 return true;
//	    }
//	    
//	}
	
	public boolean isBig() {
	    return big;
	}
	
	public void setBig(boolean value) {
	    this.big = value;
	}
	
//	public boolean isFalling() {
//	    return falling;
//	}
	
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
	    return true; // Mario pisa tierra, no se si asi esta bien, misma duda con el de goomba
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

		boolean ActionExecute = processActions(game.actions());
	   
		Position below = pos.translate(new Position(0, 1));

		if(!ActionExecute && game.isInside(below))
			super.update();
		
		else game.doInteraction(this);

	}

	private boolean processActions(Iterable<Action> actions) {
		
		
	    boolean moved = false;
	    
	    Position below = pos.translate(new Position(0, 1));
	    
	    if (game.isInside(below) && game.isSolid(below)) {
	    	
	        // Está en el suelo
	        setFalling(false);
	        jumpcont = 0;
	        
	    } else if (game.isInside(below) && !game.isSolid(below)) {
	    	
	        // Está en el aire → cae
	        pos = below;
	        setFalling(true);
	    }

	    for (Action act : actions) {
	    	
	        Position next = pos.translateMario(act);

	        // Movimiento horizontal
	        if (act == Action.LEFT || act == Action.RIGHT || act == Action.STOP) {
	        	
	            if (act == Action.LEFT) setDirx(-1);
	            else if (act == Action.RIGHT) setDirx(1);
	            else if (act == Action.STOP) setDirx(0);

	            // Actualizar icono
	            icon = switch (getDirx()) {
	            
	                case 1 -> Messages.MARIO_RIGHT;
	                case -1 -> Messages.MARIO_LEFT;
	                default -> Messages.MARIO_STOP;
	                
	            };

	            if (game.isInside(next) && game.isEmpty(next)) {
	                pos = next;
	                moved = true;
	            }
	        }

	        // Salto (doble permitido)
	        if (act == Action.UP) {
	        	
	            if (jumpcont < 2) {
	            	
	                if (game.isInside(next) && game.isEmpty(next)) {
	                	
	                    pos = next;
	                    setFalling(true);
	                    jumpcont++; // solo incrementa si realmente sube
	                }
	            }
	            moved = true;
	        }

	        // Caída rápida
	        if (act == Action.DOWN) {
//	            Position below = pos.translateMario(act);
	            if (game.isInside(below) && !game.isSolid(below)) {
	                while (game.isInside(below) && !game.isSolid(below)) {
	                    pos = below;
	                    below = pos.translateMario(act);
	                    if (!game.isInside(below)) {
	                        muere();
	                        return false;
	                    }
	                }
	                moved = true;
	            } else {
	                setDirx(0);
	                icon = Messages.MARIO_STOP;
	            }
	        }
	        
	    }
	    return moved;
	}

	
	
}
