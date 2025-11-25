//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.logic.gameobjects;

import tp1.exception.GameParseException;
import tp1.exception.OffBoardException;
import tp1.logic.*;

public abstract class GameObject implements GameItem{ 

	protected Position pos;
	private boolean isAlive;
	protected GameWorld game; 
	
	
	
	public GameObject(GameWorld game, Position pos) {
		
		this.isAlive = true;
		this.pos = pos;
		this.game = game;
		
	}
	
	public GameObject() {} //factoría

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
	
	protected abstract String getName(); //nombre de los objetos
    protected abstract String getShortcut(); //shortcut de los objetos
    protected abstract GameObject createObject(GameWorld game, Position pos); //creacion de objetos

	public GameObject parse(String[] objWords, GameWorld game, Position pos) throws GameParseException, OffBoardException {

//	    Position pos = Position.parse(objWords[0]);
//	    if (!game.isInside(pos)) 
//	    		return null;

	    String type = objWords[1];
	    if (type.equalsIgnoreCase(this.getName()) || type.equalsIgnoreCase(this.getShortcut())) {
	    	
	        return this.createObject(game, pos);

	    }
	    
//	    else if (type.equalsIgnoreCase("ExitDoor") || type.equalsIgnoreCase("ED")) 
//	        return new Exit_door(game, pos);
//
//	    else if (type.equalsIgnoreCase("Box") || type.equalsIgnoreCase("B")) 
//	        return new Box(game, pos);
	    
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