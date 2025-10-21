//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.logic;

import java.util.ArrayList;
import tp1.logic.gameobjects.*;



public class GameObjectContainer {

	ArrayList<Land> landArray = new ArrayList<>();
	ArrayList<Goomba> GoombaArray = new ArrayList<>();
	private Mario mario;
	private Exit_door exit;
	
	

	public void add(Land land){
		
		landArray.add(land);
		
	} 
	
	
	
	public void add(Goomba g){
		
		GoombaArray.add(g);
		
	} 
	
	
	
	public void add(Mario mario) {
		
		this.mario = mario;
		
	}
	
	
	
	public void add(Exit_door exit) {
		
		this.exit = exit;
		
	}
	
	
	
	// string (pos) --> recorrer lista y compara pos return land.geticono
	public String busqueda(Position pos) {
		
		StringBuilder sb = new StringBuilder();
		
		for(Land land: landArray) {
			
			if(land.isInPos(pos)){
				
				sb.append(land.getIcon());
				
			}	
		}
			
			for(Goomba goomba: GoombaArray) {
				
				if(goomba.isInPos(pos)){
					
					sb.append(goomba.getIcon());
					
				}	
			}
			

			if(mario.isInPos(pos)) {
				
				 sb.append(mario.getIcon());
				 
			 }
			
			if(exit.isInPos(pos)) {
				
				sb.append(exit.getIcon());
				
			}
			
			return  sb.toString();
			
	}
	
	
	
	public boolean solido(Position pos) {
		
	    // Revisar tierras
	    for (Land land : landArray ) {
	    	
	        if (land.isInPos(pos)) {
	        	
	            return true; 
	            
	        }
	    }
	    
	    return false;
	    
	}
	
	
	
	public boolean vacio(Position pos) {
		
        // Tierra
        for (Land land : landArray) {
        	
            if (land.isInPos(pos)) return false;
            
        }

        return true;
        
    }
		
	
	
	 public boolean dentro(Position pos) {
		 
		 	Position bordes = new Position(Game.DIM_X, Game.DIM_Y);
		    return pos.isInPosition(bordes);
		           
	 }
	 
	 
	 
	 public void update(Game game) {
		   
		 if (game.isFinished() || game.playerLoses()) return;
		   
		 if (mario.interactWith(exit)) {
			 
		   	return;
		   	
		 }
		 
				 	    
		    // 2. Actualizar todos los Goombas
		 
		 for (Goomba g : GoombaArray) {
			 
			 g.update();
			 
		 }
		 
		 game.doInteractionsFrom(mario); 
		 // 3. Eliminar Goombas muertos
		 GoombaArray.removeIf(g -> !g.Esta_vivo());
    }
	 
	 
	 
	 public Exit_door getExitDoor(){
		 
		 return this.exit;
		 
	 }
	 
	 
	 
	 public void doInteractionsFrom(Mario mario) {
		 
		    for (Goomba g : GoombaArray) {
		    	
		        mario.interactWith(g);
		        
		    }
		}
	 
	 
	 
}