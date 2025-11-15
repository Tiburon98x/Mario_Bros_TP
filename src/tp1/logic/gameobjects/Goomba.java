//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.logic.gameobjects;


import tp1.logic.*;
import tp1.view.Messages;

public class Goomba extends MovingObject {
    
	public Goomba (GameWorld game, Position pos) {
		
		super(game, pos);
		setDirx(-1);
	}
	
	public Goomba() {
		super();
	}

	@Override
	public String getIcon() {		
		return Messages.GOOMBA;		
	}
	
	public boolean interactWith(GameItem other) {
	    return false;
	}
	
	@Override
	public boolean receiveInteraction(Mario mario) {

		this.dead();
		mario.givePointsToGame(100);
        Position below = pos.translate(new Position(0, 1));

		if(!mario.isFalling() || (mario.isFalling() && game.isEmpty(below))) { //que mario este debajo de goomba
			mario.receiveInteraction(this);
		}
		return true;
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