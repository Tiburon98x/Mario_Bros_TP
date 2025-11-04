//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.logic.gameobjects;


import tp1.logic.*;
import tp1.view.Messages;


public class Goomba extends MovingObject {

	private Position pos;
	private Game game;	
    
	public Goomba (Game game, Position pos) {
		
		super(game, pos);
		setDirx(-1);
	}
	
	public String getIcon() {
		
		return Messages.GOOMBA;
		
	}
	
	@Override
	public void update() {
		
		super.update();
		game.doInteraction(this);
	}
	
//	public boolean isInPos(Position pos){
//		
//		return (this.pos.equals(pos));	
//	}
	
//	public void update() {
//		
//		  Position desplazamiento_next = new Position(getDirx(), 0);
//		  Position desplazamiento_below = new Position(0, 1);
//		  Position bordes = new Position(Game.DIM_X, Game.DIM_Y);
//	      Position below = pos.translate(desplazamiento_below);
//	      Position next = pos.translate(desplazamiento_next);
//        
//	    if (!isAlive()) 
//	    	
//	    	return;
//
//	 // 1. Si no hay suelo debajo → cae
//	    
////        if (!game.isSolid(below)) {
////        	
////            pos = below;
////            // si cae fuera del tablero → muere
////            
////            // Hay que arreglar el if de abajo!!!!!
////            if (!pos.isInPosition(bordes)) { 
////            	System.out.println("NO ESTA DENTRO");
////                muere();
////                
////            }
////            
////            return;
////            
////        }
//	    
//      if (!game.isSolid(below)) {
//    	
//        pos = below;
//        return;
//        // si cae fuera del tablero → muere
//      }
//        // Hay que arreglar el if de abajo!!!!!
//        if (!pos.isInPosition(bordes)) { 
//        	System.out.println("NO ESTA DENTRO");
//            this.dead();
//            return;
//
//        }
//                
//        // 2. Si puede avanzar en la dirección actual → avanza
//        
//        if (game.isInside(next) && game.isEmpty(next)) {
//        	
//            pos = next;
//            
//        } else {
//        	
//            // 3. Si choca con pared u objeto sólido → invierte dirección
//            setDirx(getDirx() * -1);
//            
//        }
//    }
		
//	public boolean Esta_vivo() {
//		
//		return vivo;
//	}
//	
//	public void muere() {
//		
//	        vivo = false;	  
//	}
//	
	
	
//	public boolean receiveInteraction(Mario other) {
//		
//	    this.vivo = false;
//	    other.givePointsToGame(100);
//	    return true;
//	    
//	}
	
	
	public boolean interactWith(GameItem other) {
		
		boolean success = false;
	    boolean canInteract = other.isInPosition(this.pos);
	    if (canInteract) {
	    	
	        success = other.receiveInteraction(this);         
	    }
	    return canInteract && success;
	}
	
	@Override
	public boolean receiveInteraction(Mario mario) {
		
		 this.dead(); 
	     return true;
	}

	@Override
	public boolean receiveInteraction(Goomba goomba) {
	    return true; // No estoy seguro, se chocan entre ellos no?
	}

	@Override
	public boolean receiveInteraction(Land land) {
	    return true; // No seguro, no se si esto hace que goomba detecte suelo
	}

	@Override
	public boolean receiveInteraction(Exit_door door) {
	    return false;
	}


}