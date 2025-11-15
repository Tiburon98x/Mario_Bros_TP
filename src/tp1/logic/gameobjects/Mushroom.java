package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class Mushroom extends MovingObject {

	private String icon;

//Si hay un TODO, no lo he hecho

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

//	@Override
//	public boolean receiveInteraction(Mario mario) {
//
//		this.dead();
//		mario.receiveInteraction(this);
//		return true;
//	}
	
	@Override
	public boolean receiveInteraction(Mario mario) {

	if(!mario.isBig()) {
	mario.setBig(isAlive());
	}

	this.dead();
	return true;
	}

	@Override
	public boolean receiveInteraction(Exit_door obj) {
		return false;
	}

//	@Override
//	public boolean receiveInteraction(Mario mario) {
//		if(!mario.isBig()) {
//			mario.setBig(isAlive());
//		}
//		// Tiene que desaparecer   No he tocado el Mario debería
//		this.dead(); // ????
//		return true;
//	}

	@Override
	public boolean receiveInteraction(Goomba obj) {
		return false;
	}

	@Override
	public String getIcon() {
		return icon;
	}
	
	@Override
	public void switchIcon() {}

	@Override
	public boolean receiveInteraction(Box obj) {
		return false;
	}

	@Override
	public boolean receiveInteraction(Mushroom obj) {
		return false;
	}
}
