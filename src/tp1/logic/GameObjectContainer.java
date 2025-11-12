//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.logic;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import tp1.logic.gameobjects.*;


public class GameObjectContainer{
	
	private List<GameObject> gameObjects;

	public GameObjectContainer() {
		gameObjects = new ArrayList<>();
	}
	
	
	public void addItem(GameObject obj) {
		gameObjects.add(obj);
	}
	
	
	public List<GameObject> getItems() {
		return gameObjects;
	}
	

	public String busqueda(Position pos) {
		
		StringBuilder sb = new StringBuilder();

	    for (GameObject obj : gameObjects) {
	        if (obj.isInPosition(pos)) {
	            sb.append(obj.getIcon());
	        }
	    }
	    return sb.toString();				
	}
	
	
	public boolean solido(Position pos) {
	 
		for (GameObject obj : gameObjects) {
	       
	    	if (obj.isSolid() && obj.isInPosition(pos)) {
	            return true; 
	        }	
	    }	    
	    return false;
	}

	
	public boolean vacio(Position pos) {
	    
		for (GameObject obj : gameObjects) {
	      
			if (obj.isSolid() && obj.isInPosition(pos)) {
	            return false; 
	        }
	    }
	    return true;
	}
	
	
//	public boolean dentro(Position pos) {
//		
//		Position bordes = new Position(Game.DIM_X, Game.DIM_Y);
//		return pos.isInPosition(bordes);	           
//	 }   esto está en el game ahora
	 
	 
	public void update(Game game) {
		
		// juego terminado
		if (game.isFinished() || game.playerLoses()) return;

	    
		for (GameObject obj : gameObjects) {
        obj.update();
	    }

		// eliminar los obj dead
		//gameObjects.removeIf(obj -> !obj.isAlive()); -> esta instruccion esta en el game
	   
	}

	
//	public void resolveInteractions() {
//		for (int i = 0; i < gameObjects.size(); i++) {
//			GameItem obj1 = gameObjects.get(i);
//			if (!obj1.isAlive()) continue;
//			
//			for (int j = i + 1; j < gameObjects.size(); j++) {
//				GameItem obj2 = gameObjects.get(j);
//				if (!obj2.isAlive()) continue;
//				
//				// Interacciones bidireccionales
//				obj1.interactWith(obj2);
//				obj2.interactWith(obj1);
//			}
//		}
//	}
//	
	public void doInteraction(GameItem other) {
		 
	  for (GameItem item : gameObjects) {
		  if (item != other){

			  other.interactWith(item);
			  item.interactWith(other);
		  }
	   }
		    
	}
	 	 
	public void removeDead() {
		gameObjects.removeIf(obj -> !obj.isAlive());
	}
	 
}