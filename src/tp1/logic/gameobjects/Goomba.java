//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.logic.gameobjects;


import tp1.logic.*;
import tp1.view.Messages;


public class Goomba {

	private Position pos;
	private Game game;
	private int dirX = -1; // empieza moviéndose a la izquierda
    private boolean vivo = true;
	
    
    
	public Goomba (Game game, Position pos) {
		
		this.pos = pos;
		this.game = game;
		
	}
	
	
	
	public String getIcon() {
		
		return Messages.GOOMBA;
		
	}
	
	
	
	public boolean isInPos(Position pos){
		
		return (this.pos.equals(pos));
		
	}
	
	
	
	public void update() {
		
		  Position desplazamiento_next = new Position(dirX, 0);
		  Position desplazamiento_below = new Position(0, 1);
		  Position bordes = new Position(Game.DIM_X, Game.DIM_Y);
	      Position below = pos.translate(desplazamiento_below);
	      Position next = pos.translate(desplazamiento_next);
        
	    if (!Esta_vivo()) 
	    	
	    	return;

	 // 1. Si no hay suelo debajo → cae
	    
//        if (!game.isSolid(below)) {
//        	
//            pos = below;
//            // si cae fuera del tablero → muere
//            
//            // Hay que arreglar el if de abajo!!!!!
//            if (!pos.isInPosition(bordes)) { 
//            	System.out.println("NO ESTA DENTRO");
//                muere();
//                
//            }
//            
//            return;
//            
//        }
	    
      if (!game.isSolid(below)) {
    	
        pos = below;
        return;
        // si cae fuera del tablero → muere
      }
        // Hay que arreglar el if de abajo!!!!!
        if (!pos.isInPosition(bordes)) { 
        	System.out.println("NO ESTA DENTRO");
            muere();
            return;

        }
        
        
        // 2. Si puede avanzar en la dirección actual → avanza
        
        if (game.isInside(next) && game.isEmpty(next)) {
        	
            pos = next;
            
        } else {
        	
            // 3. Si choca con pared u objeto sólido → invierte dirección
            dirX *= -1;
            
        }
    }
	
	
	
	public boolean Esta_vivo() {
		
		return vivo;
	}
	
	
	
	public void muere() {
		
	        vivo = false;
	        
	}
	
	
	
	public boolean receiveInteraction(Mario other) {
		
	    this.vivo = false;
	    other.givePointsToGame(100);
	    return true;
	    
	}
	
	
	
}