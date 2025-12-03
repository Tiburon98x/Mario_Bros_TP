//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.logic.gameobjects;

import tp1.exception.ObjectParseException;
import tp1.exception.OffBoardException;
import tp1.exception.PositionParseException;
import tp1.logic.*;
import tp1.view.Messages;

public abstract class GameObject implements GameItem{ 

	protected Position pos;
	private boolean isAlive;
	protected GameWorld game; 
	
	private  String NAME;
	private  String SHORTCUT;
	
    private static final int AllowedArgsGameObject = 2; //posicion y nombre
	
	public GameObject(GameWorld game, Position pos, String NAME, String SHORTCUT) {
		
		this.isAlive = true;
		this.pos = pos;
		this.game = game;
		this.NAME = NAME;
		this.SHORTCUT = SHORTCUT;
		
	}
	
	protected int getAllowedArgs() {
        return AllowedArgsGameObject;
    }

    public GameObject(String name, String shortcut) {
        this.NAME = name;
        this.SHORTCUT = shortcut;
    }

	public boolean isInPosition(Position p) {
	    return this.isAlive && this.pos.equals(p); 
	}
 	
	public boolean isAlive() {
		return isAlive;
	}
	
	public void dead(){
		this.isAlive = false;
	}
	
	public boolean isSolid() {
		return false;
	}
	 
	public void update() {}
	
	public abstract String toString();
	
	protected String getName() {
		return NAME;
	}

	protected String getShortcut() {
		return SHORTCUT;
	}
	
    protected abstract GameObject createObject(GameWorld game, Position pos, String[] objWords); //creacion de objetos

	public GameObject parse(String[] objWords, GameWorld game) throws ObjectParseException, OffBoardException {
		    	 	   
	    if (this.getName().equalsIgnoreCase(objWords[1]) ||  this.getShortcut().equalsIgnoreCase(objWords[1])) {
   
			if(objWords.length > this.getAllowedArgs()) //muchos args
			    	throw new ObjectParseException(Messages.OBJECT_PARSE_ERROR.formatted(String.join(" ", objWords)));
			
			Position pos; 
			try {
				pos = Position.parse(objWords[0]);
				
			} catch (PositionParseException e) {
				throw new ObjectParseException(Messages.ERROR_INVALID_POSITION.formatted(String.join(" ", objWords)), e);
			}

			if (!game.isInside(pos)) { //posicion dentro del tablero
				throw new OffBoardException(Messages.OBJECT_POSITION_OFF_BOARD.formatted(String.join(" ", objWords)));
			}

			return this.createObject(game, pos, objWords);
		}
	    
		return null;    
	}
	
	public void receiveAction(Action act){}
	
	public String stringify() {
        StringBuilder sb = new StringBuilder();
        sb.append(pos.toString()); 
        sb.append(" ").append(this.getName()); //Coge los nombres de los objetos
        return sb.toString();
    }
	
}