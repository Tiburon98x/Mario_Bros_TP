//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.logic.gameobjects;

import tp1.logic.*;
import tp1.view.Messages;

public class Land extends GameObject {
	
	private String icon;
		
	public Land(GameWorld game, Position pos) {
		
		super(game, pos);
		this.icon = Messages.LAND;
		
	}
	
	public Land() {
		super();
	}
	
	@Override
	public String toString() {
	    return this.icon;
	}
	
	@Override
	public boolean interactWith(GameItem other) {
		return false;
	}

	@Override
	public boolean receiveInteraction(Land obj) {
		return false;
	}

	@Override
	public boolean receiveInteraction(Exit_door obj) {
		return false;
	}

	@Override
	public boolean receiveInteraction(Mario obj) {
		return false;
	}

	@Override
	public boolean receiveInteraction(Goomba obj) {
		return false;
	}

	@Override
	public void update() {}

	@Override
	public boolean isSolid() {
		return true;
	}

	@Override
	public boolean receiveInteraction(Box obj) {
		return false;
	}

	@Override
	public boolean receiveInteraction(Mushroom obj) {
		return false;
	}

	
	
}