//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.logic;

import tp1.exception.ActionParseException;
import tp1.view.Messages;

public enum Action {
	
	LEFT(-1,0), RIGHT(1,0), DOWN(0,1), UP(0,-1), STOP(0,0), INCORRECT(-1, -1);
	
	@SuppressWarnings("unused")
	private int x;
	@SuppressWarnings("unused")
	private int y;
		
	private Action(int x, int y) {
		
		this.x=x;
		this.y=y;		
	}		
	
	public static Action parseAction(String str) throws ActionParseException {
    	
        str = str.toLowerCase();
        switch (str) {
            case "l":
            case "left":
                return Action.LEFT;
            case "r":
            case "right":
                return Action.RIGHT;
            case "u":
            case "up":
                return Action.UP;
            case "d":
            case "down":
                return Action.DOWN;
            case "s":
            case "stop":
                return Action.STOP;
            default:        	
                throw new ActionParseException(Messages.UNKNOWN_ACTION.formatted(str)); 
                //si las acciones introducidas son incorrectas (-1, -1) 
              //hay que lanzar una excepcion y tratarla en el Execute de aquí, que lanzará otro
               // exception que irá al controller, siendo un CommandExecuteException
        }
    }

	int getX() {
		return x;
	}

	int getY() {
		return y;
	}
	
}