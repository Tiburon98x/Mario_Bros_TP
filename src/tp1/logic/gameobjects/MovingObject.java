//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.logic.gameobjects;

import tp1.exception.ActionParseException;
import tp1.exception.ObjectParseException;
import tp1.exception.OffBoardException;
import tp1.logic.Action;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public abstract class MovingObject extends GameObject {


	private int dirx;
	private boolean isFalling;
    
//	private  String NAME;
//    private  String SHORTCUT;

	
    protected abstract MovingObject createObject(GameWorld game, Position pos, int Dirx); //creacion de objetos
	
	public MovingObject(GameWorld game, Position pos, String NAME, String SHORTCUT) {  // DUDOSO String NAME, String SHORTCUT
//		super(game, pos);
		super(game, pos, NAME, SHORTCUT);
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
	public GameObject parse(String[] objWords, GameWorld game) throws ObjectParseException, OffBoardException  {

//		String type = objWords[1];
//		
//	    if (!type.equalsIgnoreCase(this.getName()) && !type.equalsIgnoreCase(this.getShortcut())) {
//	        return null; 
//	    }
//
//	    Position pos;
//	    try {
//	        pos = Position.parse(objWords[0]);
//	        
//	    } catch (PositionParseException e) {
//	        throw new ObjectParseException(e.getMessage(), e);
//	    }
//
//	    if (!game.isInside(pos)) {
//	        throw new OffBoardException(Messages.OBJECT_POSITION_OFF_BOARD.formatted(String.join(" ", objWords)));
//	    }
	    
		GameObject obj = super.parse(objWords, game);
	    
	    if (obj == null) return null;
	    
	    MovingObject mObj = (MovingObject) obj; //CASTING, SE PUEDE??, si no se puede, la solucion
	    //seria repetir codigo y crear movingObject obj (está todo en comentarios)
	    
//	    if (objWords.length > 2) {
//	        if (objWords[2].equalsIgnoreCase("RIGHT") || objWords[2].equalsIgnoreCase("R")) 
//	            this.dirx = 1;
//	        
//	        else if (objWords[2].equalsIgnoreCase("LEFT") || objWords[2].equalsIgnoreCase("L")) 
//	            this.dirx = -1;       
//	    }
	    
	    
	 //   MovingObject obj = this.createObject(game, pos, dirx);
	    
	//    if (dirx == -1) obj.cambiarIconIzq();

	    //------------AJUSTE DE DIRECCIONES
//	    if (dirx != 0) { // Solo si ha cambiado
//	        mObj.setDirx(dirx);
//	        if (dirx == -1) mObj.cambiarIconIzq();
//	    }
	    
	    if (objWords.length > 2) {
            String dirString = objWords[2];
            String originalText = String.join(" ", objWords);
            
            Action action = parseDirection(dirString, originalText);
            
            mObj.setDirection(action, originalText);
        }
	    
	  //  return obj;
	    return mObj;
	}
		
	@Override
	protected GameObject createObject(GameWorld game, Position pos) {	 
	    return this.createObject(game, pos, 0); //los MOVOBJ necesitan la dirx 
	}
	
	@Override
    public String stringify() {
        StringBuilder sb = new StringBuilder(super.stringify());
        if (dirx == 1) sb.append(" RIGHT"); //direcciones
        else if (dirx == -1) sb.append(" LEFT");
      //  else sb.append("STOP"); 
        
        return sb.toString();
    }
	
	private Action parseDirection(String dirString, String originalText) throws ObjectParseException {
        try {
            return Action.parseAction(dirString);
        } catch (ActionParseException e) {
            throw new ObjectParseException(Messages.ERROR_UNKNOWN_DIRECTION.formatted(originalText), e);
        }
    }
	
	private void setDirection(Action action, String originalText) throws ObjectParseException {
        switch (action) {
            case RIGHT:
                this.setDirx(1);
                this.cambiarIconDer();
                break;
            case LEFT:
                this.setDirx(-1);
                this.cambiarIconIzq();
                break;
            case STOP:
                this.setDirx(0);
                break;
            default:
                throw new ObjectParseException(Messages.ERROR_INVALID_DIRECTION.formatted(originalText));
        }
    }
	
}