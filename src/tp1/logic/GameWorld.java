package tp1.logic;

import tp1.logic.gameobjects.GameItem;

//para gameobject
public interface GameWorld {

	public boolean isSolid(Position pos);
	public int addPoints(int p);
	public void marioExited();
	public boolean isEmpty(Position pos);
	public boolean isInside(Position pos);
	public void marioMuere();
	public void doInteraction(GameItem other);
}
