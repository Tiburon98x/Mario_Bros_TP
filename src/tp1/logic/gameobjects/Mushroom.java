//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class Mushroom extends MovingObject {

	private String icon;
	private static final String NAME = Messages.OBJECT_MUSHROOM;
    private static final String SHORTCUT = Messages.OBJECT_MUSHROOM_SHORTCUT;


	public Mushroom(GameWorld game, Position pos) {
		
		super(game, pos, NAME, SHORTCUT);
		this.icon = Messages.MUSHROOM;
		setDirx(1);
		
	}

	public Mushroom() {
		super(NAME, SHORTCUT);
	}

	@Override
	public MovingObject createObject(GameWorld game, Position pos, int Dirx) {
		Mushroom m = new Mushroom(game, pos);
		m.setDirx(Dirx);
		return m;
	}
	
	@Override
	public boolean interactWith(GameItem item) {
		return false;
	}

	@Override
	public boolean receiveInteraction(Land obj) {
		return false;
	}

	@Override
	public boolean receiveInteraction(Mario mario) {

		this.dead();
		mario.receiveInteraction(this);
		return true;
	
	}

	@Override
	public boolean receiveInteraction(Exit_door obj) {
		return false;
	}

	@Override
	public boolean receiveInteraction(Goomba obj) {
		return false;
	}

	@Override
	public boolean receiveInteraction(Box obj) {
		return false;
	}

	@Override
	public boolean receiveInteraction(Mushroom obj) {
		return false;
	}

	@Override
	public void cambiarIconIzq() {}

	@Override
	public void cambiarIconDer() {}

	@Override
	public String toString() {
	    return this.icon;
	}

	@Override
	protected GameObject createObject(GameWorld game, Position pos, String[] s) {
		return new Mushroom(game, pos);
	}
	
}