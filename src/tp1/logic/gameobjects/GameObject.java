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

//	// Not mandatory but recommended
//	protected void move(Action dir) {
//		// TODO Auto-generated method stub
//	}
	
	
}