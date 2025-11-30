//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.logic.gameobjects;


import tp1.logic.*;
import tp1.view.Messages;



public class Goomba extends MovingObject {
	
	private String icon;
	private static final String NAME = Messages.OBJECT_GOOMBA;
    private static final String SHORTCUT = Messages.OBJECT_GOOMBA_SHORTCUT;

    
	public Goomba (GameWorld game, Position pos) {
		
//		super(game, pos);
		super(game, pos, NAME, SHORTCUT);
		setDirx(-1);
		this.icon = Messages.GOOMBA;
		
	}
	
	public Goomba() {
		super();
	}
	
//	@Override
//	public String getName() {
//		return NAME;
//	}
//	
//	@Override
//	public String getShortcut() {
//		return SHORTCUT;
//	}
	
	@Override
	public MovingObject createObject(GameWorld game, Position pos, int Dirx) {
		Goomba g = new Goomba(game, pos);
		g.setDirx(Dirx);
		return g;
	}
	
	@Override
	public boolean interactWith(GameItem other) {
	    return false;
	}
	
	@Override
	public boolean receiveInteraction(Mario mario) {
		
		if(mario.isAlive()) {
			
			this.dead();
			mario.givePointsToGame(100);
	        Position below = pos.translate(new Position(0, 1));
			if(!mario.isFalling() || (mario.isInPosition(below)))  
				mario.receiveInteraction(this);			
			
			return true;			
		}		
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
	public void cambiarIconIzq() {}

	@Override
	public void cambiarIconDer() {}

	@Override
	public String toString() {
	    return this.icon;
	}

	@Override
	protected GameObject createObject(GameWorld game, Position pos) {
		return null;
	}	//no se va a usar este, se usará el movingObject
	
}