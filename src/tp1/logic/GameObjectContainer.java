//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.logic;


import java.util.ArrayList;
import java.util.List;
import tp1.logic.gameobjects.*;


public class GameObjectContainer{
	
	private List<GameObject> gameObjects;
	
	private List<GameObject> aux; 

	public GameObjectContainer() {
		gameObjects = new ArrayList<>();
		aux = new ArrayList<>();
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
	
	public void update(Game game) {
		
		if (game.isFinished() || game.playerLoses()) return;
   
		for (GameObject obj : gameObjects) {
        obj.update();
	    }
		
		for (GameObject obj : gameObjects) {
	        doInteraction(obj);
		}
		
		if (!aux.isEmpty()) {
	        gameObjects.addAll(aux);
	        aux.clear();
	    }

	}

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


	public void addLater(GameObject obj) {
		aux.add(obj);		
	}
	 
}