//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.logic.gameobjects;


import tp1.logic.*;
import tp1.view.Messages;


public class Goomba extends MovingObject {
    
	public Goomba (GameWorld game, Position pos) {
		
		super(game, pos);
		setDirx(-1);
	}
	
	@Override
	public String getIcon() {		
		return Messages.GOOMBA;		
	}
	
	@Override
	public void update() {
		
		super.update();
//		game.doInteraction(this);
	}

	
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
		if(mario.isFalling()) {
			this.dead();
			mario.givePointsToGame(100);
		}
		return true;
	}

	@Override
	public boolean receiveInteraction(Goomba goomba) {
	    return false; // No estoy seguro, se chocan entre ellos no?
	}

	@Override
	public boolean receiveInteraction(Land land) {
	    return false; // No seguro, no se si esto hace que goomba detecte suelo
	}

	@Override
	public boolean receiveInteraction(Exit_door door) {
	    return false;
	}

	@Override
	public void switchIcon() {}

}