package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class Box extends GameObject{

	private String icon;

	public Box(GameWorld game, Position pos) {
		super(game, pos);
		this.icon = Messages.BOX;
	}

	public Box() {
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
	public boolean receiveInteraction(Exit_door obj) {
		return false;
	}

//	@Override
//	public boolean receiveInteraction(Mario mario) {
//		
//		Position below = pos.translate(new Position(0, 1));
//		
//		if(mario.isInPosition(below) && isAlive()) {
//			
//			Position top = pos.translate(new Position(0, -1));
//			Mushroom seta = new Mushroom(game, top);
//			
////			game.addObj(seta); //añadir seta a la lista de objetos de game
//			mario.givePointsToGame(50);
//			this.dead();
//			this.icon = Messages.EMPTY_BOX;
//
//			return true;
//		}
//		return false;
//	}
	@Override
	public boolean receiveInteraction(Mario mario) {
		
	   if (mario.isDirectlyBelow(this.pos) && Messages.BOX.equals(this.icon)) {
	       mario.givePointsToGame(50);
	       this.icon = Messages.EMPTY_BOX;
	       Mushroom mushroom = new Mushroom(game, this.pos.translate(new Position(0, -1)));
	       game.addGameObject(mushroom);
	       return true;
	     
	   }
	   return false;
	}

	@Override
	public boolean receiveInteraction(Goomba obj) {
		return false;
	}

	@Override
	public void update() {}

	@Override
	public String getIcon() {
		return icon;
	}

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