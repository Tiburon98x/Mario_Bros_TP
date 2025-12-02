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
	
    public static final int AllowedArgsMovingObject = 3; //posicion, nombre y dirección

    
//	private  String NAME;
//    private  String SHORTCUT;


    protected abstract MovingObject createObject(GameWorld game, Position pos, int Dirx); //creacion de objetos
	
    @Override
    protected int getAllowedArgs() {
        return AllowedArgsMovingObject; 
    }
    
	public MovingObject(GameWorld game, Position pos, String NAME, String SHORTCUT) {  // DUDOSO String NAME, String SHORTCUT
//		super(game, pos);
		super(game, pos, NAME, SHORTCUT);
	}
	
	public MovingObject(String name, String shortcut) {
		super(name, shortcut);
	}
	
//	public MovingObject() {
//		super();
//	}

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
	    
		GameObject obj = super.parse(objWords, game);
		if (obj == null) 
			return null;
        
        MovingObject mObj = (MovingObject) obj;
        
        if (objWords.length > 2) { 
        	
            String dirString = objWords[2];
            String originalText = String.join(" ", objWords);
           
            // Aquí capturamos el error "NORTH"
            Action action = parseDirection(dirString, originalText);
            mObj.setDirection(action, originalText);
        }
        
        return mObj;
//		if (obj != null) {
//            MovingObject mObj = (MovingObject) obj;
//            
//            // Si hay dirección (indice 2)
//            if (objWords.length > 2) {
//            	
//            	String dirString = objWords[2];
//            	Action dirAux = parseDirection(dirString, objWords);
//            	applyDirection(mObj, dirAux);
//            }
//		}
//	    
//	    return obj;
	}
		
	@Override
	protected GameObject createObject(GameWorld game, Position pos, String[] s) {	 
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
	
//	private boolean isValidDirection(Action action) {
//        return action == Action.LEFT || action == Action.RIGHT || action == Action.STOP;
//    }
//	
//	private void applyDirection(MovingObject mObj, Action action) {
//        switch (action) {
//            case RIGHT:
//                mObj.setDirx(1);
//                mObj.cambiarIconDer();
//                break;
//            case LEFT:
//                mObj.setDirx(-1);
//                mObj.cambiarIconIzq();
//                break;
//            default: // STOP
//                mObj.setDirx(0);
//                break;
//        }
//    }
	
//	private Action parseDirection(String dirString, String[] objWords) throws ObjectParseException {
//		 try {
//
//             Action dirAux = Action.parseAction(objWords[2]);
//             System.out.println("PARSE GAMEOBJECT");
//             if (isValidDirection(dirAux)) {
//            	 
//            	 return dirAux;
//            	 
//             } else {
//                 
//                 throw new ObjectParseException(Messages.ERROR_INVALID_DIRECTION.formatted(String.join(" ", objWords)));
//             }
//             
//         } catch (ActionParseException e) {
//             
//             throw new ObjectParseException(Messages.ERROR_UNKNOWN_DIRECTION.formatted(String.join(" ", objWords)), e);
//         }
//    }
	
	private Action parseDirection(String dirString, String originalText) throws ObjectParseException {
		
        try {
            return Action.parseAction(dirString);
        } catch (Exception e) {
        	
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
	            // Si la acción existe (ej: UP, DOWN, JUMP) pero no es válida para moverse:
	            // El mensaje del log dice: "Invalid moving object direction"
	            throw new ObjectParseException(Messages.ERROR_INVALID_DIRECTION.formatted(originalText));
	    }
	}
	
}