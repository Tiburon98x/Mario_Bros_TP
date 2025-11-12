//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.logic;

import java.util.ArrayList; 
import java.util.List;


public class ActionList {

	private List<Action> actions = new ArrayList<>();
	
    // Añade una acción a la lista
    public boolean add(Action act) {
    	
       if(act == null) return false;

       if ((act == Action.LEFT && actions.contains(Action.RIGHT)) ||
           (act == Action.RIGHT && actions.contains(Action.LEFT))) {
    	   
           return false;          
       }

       if ((act == Action.UP && actions.contains(Action.DOWN)) ||
           (act == Action.DOWN && actions.contains(Action.UP))) {
    	   
           return false;          
       }

       actions.add(act);
       return true;      
    }
    
    // Devuelve la lista de acciones y la vacía
    public Iterable<Action> IterableAndClear() {
    	
        List<Action> copy = new ArrayList<>(actions);
        actions.clear();
        return copy;       
    }

    // Devuelve si hay acciones pendientes
    public boolean isEmpty() {   	
        return actions.isEmpty();        
    }      
}