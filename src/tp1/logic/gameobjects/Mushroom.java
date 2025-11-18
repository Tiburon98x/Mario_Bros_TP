package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class Mushroom extends MovingObject {

	private String icon;

	

	public Mushroom(GameWorld game, Position pos) {
		
		super(game, pos);
		this.icon = Messages.MUSHROOM;
		setDirx(1);
		
	}

	public Mushroom() {
		super();
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
	public String toString() {
	    return this.icon;
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
	
	
	
}