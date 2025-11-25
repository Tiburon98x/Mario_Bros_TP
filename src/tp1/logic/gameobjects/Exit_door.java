//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.logic.gameobjects;

import tp1.logic.*;
import tp1.view.Messages;


public class Exit_door extends GameObject {
		
	private String icon;
	private static final String NAME = Messages.OBJECT_EXIT_DOOR;
    private static final String SHORTCUT = Messages.OBJECT_EXIT_DOOR_SHORTCUT;

	
	public Exit_door(GameWorld game, Position pos) {	
		
		super(game, pos);
		this.icon = Messages.EXIT_DOOR;
		
	}
			
	public Exit_door() {
		super();
	}
		
	@Override
	public String getName() {
		return NAME;
	}
	
	@Override
	public String getShortcut() {
		return SHORTCUT;
	}
	
	@Override
	public GameObject createObject(GameWorld game, Position pos) {
		return new Exit_door(game, pos);
	}
	
	@Override
	public boolean interactWith(GameItem other) {
			
		boolean success = false;
	    boolean canInteract = other.isInPosition(this.pos);
	    if (canInteract) 		    	
	        success = other.receiveInteraction(this); 
	    
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
	public boolean receiveInteraction(Box obj) {
		return false;
	}

	@Override
	public boolean receiveInteraction(Mushroom obj) {
		return false;
	}

	@Override
	public String toString() {
	    return this.icon;
	}	
			
}