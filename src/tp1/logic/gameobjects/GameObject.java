//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.logic.gameobjects;

import tp1.logic.*;

public abstract class GameObject implements GameItem{ 

	protected Position pos; // If you can, make it private.
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
	 
	public abstract void update();
	
	public abstract String getIcon();

	public GameObject parse(String[] objWords, GameWorld game) {

	    Position pos = Position.parse(objWords[0]);

	    if (!game.isInside(pos)) return null;

	    String type = objWords[1];

	    if (type.equalsIgnoreCase("Land")) { //|| type.equalsIgnoreCase("L")

	        return new Land(game, pos);
	    }

	    else if (type.equalsIgnoreCase("ExitDoor")) {

	        return new Exit_door(game, pos);
	    }

	    else if (type.equalsIgnoreCase("Box")) {

	        return new Box(game, pos);
	    }
	    // Si no coincide, devolvemos null

	    return null;
	}
	
}