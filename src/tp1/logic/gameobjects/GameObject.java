//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.logic.gameobjects;

import tp1.exception.GameParseException;
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
	
    
	
	public GameObject(GameWorld game, Position pos, String NAME, String SHORTCUT) { // DUDOSO , String NAME, String SHORTCUT
		
		this.isAlive = true;
		this.pos = pos;
		this.game = game;
		//¿?
		this.NAME = NAME;
		this.SHORTCUT = SHORTCUT;
		
	}
	
	//public GameObject() {} //factoría
	
	// CONSTRUCTOR 2: Para la Factory (Prototipo)
    // Este es el que soluciona tu NullPointerException
    public GameObject(String name, String shortcut) {
        this.NAME = name;
        this.SHORTCUT = shortcut;
        // No necesitamos pos ni game aquí, solo saber CÓMO se llama el objeto
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
	
//	public abstract String getIcon();
	public abstract String toString();
	
	//¿?
	protected String getName() {
		return NAME;
	}
	//¿?
	protected String getShortcut() {
		return SHORTCUT;
	}
	
    protected abstract GameObject createObject(GameWorld game, Position pos, String[] objWords); //creacion de objetos

	public GameObject parse(String[] objWords, GameWorld game) throws ObjectParseException, OffBoardException {
	
		// 1. PROTECCIÓN: Si no hay suficientes palabras, no es responsabilidad de este objeto parsearlo.
	    // Necesitamos al menos [Posición] y [Nombre], es decir, longitud 2.
	    if (objWords.length < 2) 
	        return null;
	    		
	    
	    
	    
		if (this.getName().equalsIgnoreCase(objWords[1]) ||  this.getShortcut().equalsIgnoreCase(objWords[1])) {
		        
			Position pos;
			try {
				pos = Position.parse(objWords[0]);
				
			} catch (PositionParseException e) {
				throw new ObjectParseException(Messages.ERROR_INVALID_POSITION.formatted(String.join(" ", objWords)), e);
			}

			if (!game.isInside(pos)) {
				throw new OffBoardException(Messages.OBJECT_POSITION_OFF_BOARD.formatted(String.join(" ", objWords)));
			}

			return this.createObject(game, pos, objWords);
		}
		return null; //o hacemos un gameParseException?	    
	}
	
	public void receiveAction(Action act){}
	
	public String stringify() {
        StringBuilder sb = new StringBuilder();
        sb.append(pos.toString()); 
        sb.append(" ").append(this.getName()); //Coge los nombres de los objetos
        return sb.toString();
    }

	
}