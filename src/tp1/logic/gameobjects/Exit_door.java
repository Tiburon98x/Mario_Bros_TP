//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.logic.gameobjects;

import tp1.logic.*;
import tp1.view.Messages;


public class Exit_door extends GameObject {
		
	public Exit_door(GameWorld game, Position pos) {	
		super(game, pos);
	}
				
	@Override
	public String getIcon() {			
		return Messages.EXIT_DOOR;	
	}
		
	@Override
	public boolean interactWith(GameItem other) {
			
		boolean success = false;
	    boolean canInteract = other.isInPosition(this.pos);
	    if (canInteract) {
		    	
	        success = other.receiveInteraction(this);         
	    }
	    return canInteract && success;
	}
		
	@Override
	public boolean receiveInteraction(Mario mario) {		 
	    return false;
	}

	@Override
	public boolean receiveInteraction(Goomba goomba) {
	    return false;
	}
	
	@Override
	public boolean receiveInteraction(Land land) {
	    return false;
	}

	@Override
	public boolean receiveInteraction(Exit_door door) {
	    return false;
	}

	@Override
	public void update() {
//		game.doInteraction(this);
	}	
		
}