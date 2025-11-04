package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;

public abstract class MovingObject extends GameObject {


	private int dirx;
	private boolean isFalling;
	
	public MovingObject(Game game, Position pos) {
		super(game, pos);
	}
	
	@Override
	public String getIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setFalling(boolean falling) {
	    this.isFalling = falling;
	}

	public boolean isFalling() {
	    return isFalling;
	}
	
	public void setDirx(int x) {
		this.dirx = x;
	}
	
	public int getDirx() {
		return dirx;
	}
	
	@Override
    public void update() {
		 
        if (!isAlive()) return;

        Position next = pos.translate(new Position(getDirx(), 0));
        Position below = pos.translate(new Position(0, 1));

        // No hay suelo, cae
        if (game.isInside(below) && !game.isSolid(below)) {
        	        	
            pos = below;
            setFalling(true);
            
            if(!game.isInside(below))
            	dead(); //si está fuera del tablero, muere
            return; 
        }
        else {
        	setFalling(false);
        }

        // Suelo y direccion auto
        if (game.isInside(next) && !game.isSolid(next)) {
            pos = next;
        } 
        else {
            setDirx(getDirx() * -1);
        }
        game.doInteraction(this);
    }

}
