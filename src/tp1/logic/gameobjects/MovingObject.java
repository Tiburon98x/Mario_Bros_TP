//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class MovingObject extends GameObject {


	private int dirx;
	private boolean isFalling;
	
	public MovingObject(GameWorld game, Position pos) {
		super(game, pos);
	}
	
	public MovingObject() {
		super();
	}

	@Override
	public abstract String getIcon();

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
        if (!game.isInside(below)) {
            dead(); // Si la posición debajo está fuera, muere
            return;
        }
        if (game.isInside(below) && !game.isSolid(below)) {
    
        	setFalling(true);        	
            pos = below;
        
        }
        else {
        	setFalling(false);
        	if (game.isInside(next) && !game.isSolid(next)) {
                pos = next;
            } 
            else {
                setDirx(getDirx() * -1);
                switchIcon();
            }
        }
        
        game.doInteraction(this);
    }

	public abstract void switchIcon();
	
	
	@Override
	public GameObject parse(String[] objWords, GameWorld game) {

	    Position pos = Position.parse(objWords[0]);

	    if (!game.isInside(pos)) return null;

	    String type = objWords[1];

	    int dirx = 0;

	    if (objWords.length > 2) {

	        if (objWords[2].equalsIgnoreCase("RIGHT")) dirx = 1;

	        else if (objWords[2].equalsIgnoreCase("LEFT")) dirx = -1;

	    }

	    if (type.equalsIgnoreCase("Goomba")) {

	        Goomba g = new Goomba(game, pos);

	        g.setDirx(dirx);

	        return g;

	    }

	    else if (type.equalsIgnoreCase("Mushroom")) {

	        Mushroom m = new Mushroom(game, pos);

	        m.setDirx(dirx);

	        return m;

	    }

	    else if (type.equalsIgnoreCase("Mario")) {

	        // delegamos en Mario.parse para tamaño

	        return new Mario().parse(objWords, game);

	    }
	    return null;
	}

}
