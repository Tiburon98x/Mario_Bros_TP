//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.logic.gameobjects;

import tp1.logic.*;
import tp1.view.Messages;

public class Land extends GameObject {
		
	public Land(GameWorld game, Position pos) {
		super(game, pos);
	}
	
	@Override
	public String getIcon() {
		return Messages.LAND;
	}
	

	@Override
	public boolean interactWith(GameItem other) {
		
//		boolean success = false;
//	    boolean canInteract = other.isInPosition(this.pos);
//	    if (canInteract) {
//	    	
//	        success = other.receiveInteraction(this);         
//	    }
//	    return canInteract && success;
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
	public void update() {
//		game.doInteraction(this);
	}

	@Override
	public boolean isSolid() {
		return true;
	}

	
}

