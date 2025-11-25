//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class MovingObject extends GameObject {


	private int dirx;
	private boolean isFalling;
    protected abstract MovingObject createObject(GameWorld game, Position pos, int Dirx); //creacion de objetos

	
	
	public MovingObject(GameWorld game, Position pos) {
		super(game, pos);
	}
	
	public MovingObject() {
		super();
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
	
	public void switchDirx() {
		this.dirx = this.dirx * -1;
		if(dirx == -1) {
			cambiarIconIzq();
		}
		else
			cambiarIconDer();
	}
		
	@Override
    public void update() {
		 
        if (!isAlive())
        		return;

        Position next = pos.translate(new Position(dirx, 0));
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
	        	if (game.isInside(next) && !game.isSolid(next)) 
	                pos = next;
	           
	            else {
	            	
	                setDirx(dirx * -1);	               
	                if(dirx == -1) 
	                		cambiarIconIzq();
	        		
	        		else if(dirx== 1) 
	        			cambiarIconDer();
	        		
	            }
	        	
        }
        
        game.doInteraction(this);
    }

	public abstract void cambiarIconIzq();
	public abstract void cambiarIconDer();

	@Override
	public GameObject parse(String[] objWords, GameWorld game, Position pos) {

//	   Position pos = Position.parse(objWords[0]);
//	    if (!game.isInside(pos)) 
//	    		return null;

	    String type = objWords[1];
	    int dirx = 0;
	    if (objWords.length > 2) {

	        if (objWords[2].equalsIgnoreCase("RIGHT") || objWords[2].equalsIgnoreCase("R")) 
	        		dirx = 1;

	        else if (objWords[2].equalsIgnoreCase("LEFT") || objWords[2].equalsIgnoreCase("L"))
	        		dirx = -1;

	    }

//	    if (type.equalsIgnoreCase("Goomba") || type.equalsIgnoreCase("G")) {
//
//	        Goomba g = new Goomba(game, pos);
//	        g.setDirx(dirx);
//	        return g;
//
//	    }
//
//	    else if (type.equalsIgnoreCase("Mushroom") || type.equalsIgnoreCase("MU")) {
//
//	        Mushroom m = new Mushroom(game, pos);
//	        m.setDirx(dirx);
//	        return m;
//	        
//	    }
	    if (type.equalsIgnoreCase(this.getName()) || type.equalsIgnoreCase(this.getShortcut())) 
	    	
	    	return createObject(game, pos, dirx); //mario tiene su propio parse
	    		    
	    return null;
	}

	@Override
    public String stringify() {
        StringBuilder sb = new StringBuilder(super.stringify());
        if (dirx == 1) sb.append(" RIGHT"); //direcciones
        else if (dirx == -1) sb.append(" LEFT");
      //  else sb.append("STOP"); 
        
        return sb.toString();
    }
	
}