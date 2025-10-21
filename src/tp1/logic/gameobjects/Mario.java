//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.logic.gameobjects;


import tp1.view.Messages;
import tp1.logic.*;



public class Mario {

	private Position pos;
	private Game game;
	private boolean vivo = true;
    private int dirX = 1;
	private String icon;
	private boolean big = false;
	private boolean falling = false;
	private int jumpcont = 0;



	
	public Mario(Game game, Position pos) {
		
		this.pos = pos;
		this.game = game;
		this.icon = Messages.MARIO_RIGHT;
		
	}
	

	
	public String getIcon() {
		
		return icon;
		
	}
	
	public boolean isInPos(Position pos){
		
		return this.pos.equals(pos);
	
	}
	
	public boolean Esta_vivo() {
		
        return vivo;
    
	}
	
	
	public void update(Iterable<Action> actions) {
		
	    boolean moved = false;

	    if (!vivo) return;
	    
	    Position below = pos.translate(new Position(0, 1));
	    
	    if (game.isInside(below) && game.isSolid(below)) {
	    	
	        // Está en el suelo
	        falling = false;
	        jumpcont = 0;
	        
	    } else if (game.isInside(below) && !game.isSolid(below)) {
	    	
	        // Está en el aire → cae
	        pos = below;
	        falling = true;
	    }

	    for (Action act : actions) {
	    	
	        Position next = pos.translateMario(act);

	        // Movimiento horizontal
	        if (act == Action.LEFT || act == Action.RIGHT || act == Action.STOP) {
	        	
	            if (act == Action.LEFT) dirX = -1;
	            else if (act == Action.RIGHT) dirX = 1;
	            else if (act == Action.STOP) dirX = 0;

	            // Actualizar icono
	            icon = switch (dirX) {
	            
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
	                    falling = true;
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
	                        return;
	                    }
	                }
	                moved = true;
	            } else {
	                dirX = 0;
	                icon = Messages.MARIO_STOP;
	            }
	        }
	    }


	    // Movimiento horizontal automático solo si no se movió
	    if (!moved) {
	        Position next = pos.translate(new Position(dirX, 0));
	        if (game.isInside(next) && game.isEmpty(next)) {
	            pos = next;
	        }
	    }
	}

	
	
	public void muere() {
		vivo = false;
		game.marioMuere(); // avisa al Game
	}

//	public void addAction(Action act) {
//	    actions.add(act);
//		
//	}
	//Hay que quitar el get position
	public boolean interactWith(Exit_door other) {
		
//	    if (this.pos.equals(other.getPosition())) {
	    if (other.isInPos(this.pos)) {
	        game.marioExited(); // avisa al Game de que ha llegado
	        return true;
	    }
	    return false;
	}

	public boolean interactWith(Goomba other) {
		
//	    if (!this.pos.equals(other.getPosition())) return false;
		if (!other.isInPos(this.pos)) return false;
	    if (isFalling()) { // método que indica si Mario está cayendo
	        other.receiveInteraction(this);
	        return true;
	    }

	    if (this.isBig()) {	
	        other.receiveInteraction(this);
	        this.setBig(false);
	        return true;
	    }   
	     else {
	    	 this.muere();
	    	 return true;
//	    	if (game.numLives() > 1) {
//	            this.muere(); 
//	            
//	        }
//	    	else {	            
//	            this.vivo = false;
//	            game.marioMuere(); 
//	            
//	        }
//	        return true;
	    }
	    
	}
	
	public boolean isBig() {
	    return big;
	}
	
	public void setBig(boolean value) {
	    this.big = value;
//	    if(big) {
//	        this.icon = Messages.MARIO_BIG;  // nuevo icono, aun no existe en messages
//	    }
//	    else
//            this.icon = Messages.MARIO_RIGHT; 

	}
	
	public boolean isFalling() {
	    return falling;
	}
	
	public void givePointsToGame(int pts) {
	    game.addPoints(pts);
	}
	
}
