//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.logic.gameobjects;


import tp1.view.Messages;
import tp1.exception.ObjectParseException;
import tp1.exception.OffBoardException;
import tp1.logic.*;



public class Mario extends MovingObject{

	private String icon;
	private static final String NAME = Messages.OBJECT_MARIO;
    private static final String SHORTCUT = Messages.OBJECT_MARIO_SHORTCUT;
    private static final int AllowedArgsGameObject = 4;

	private boolean big = true;
    private ActionList actionList;
	
    
    
	public Mario(GameWorld game, Position pos) {
		
//		super(game, pos);
		super(game, pos, NAME, SHORTCUT);
		this.icon = Messages.MARIO_RIGHT;
		setFalling(false);
		setDirx(1);
		this.actionList = new ActionList();
		big = true;

	}
	
	public Mario() {
		super(NAME, SHORTCUT);
		//super();
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
	
	//Posiblemente hay que eliminarlo
	@Override
	public MovingObject createObject(GameWorld game, Position pos, int Dirx) {
		Mario m = new Mario(game, pos);
		m.setDirx(Dirx);
		return m;
	}
	
	@Override
	public void cambiarIconIzq() {
		this.icon = Messages.MARIO_LEFT;
	}
	
	@Override
	public void cambiarIconDer() {
		this.icon = Messages.MARIO_RIGHT;
	}
	
	@Override
	public boolean isInPosition(Position pos){
			
		boolean isInPos = this.pos.equals(pos);	
		if (big) {

			Position topPos = this.pos.translate(new Position(0, -1));
			isInPos = this.pos.equals(pos) || topPos.equals(pos);
			
		}
		
		return isInPos;
		
	}
	
	private boolean canMoveTo(Position target) {
		
	    if (!game.isInside(target) || !game.isEmpty(target)) {
	    
		    	if(!isFalling())
		    		switchDirx();
		    	
		    return false;
	    }
	    
	    if (big) {
	    	
	        Position topTarget = target.translate(new Position(0, -1));
	        if (!game.isInside(topTarget) || !game.isEmpty(topTarget)) 
	            return false; 
	        
	    }  
	    
	    return true;
	    
	}
	
	public void muere() {
		
		this.dead();
		game.marioMuere();
		
	}
	
	public void setBig(boolean value) {
	    this.big = value;
	}
		
	public void givePointsToGame(int pts) {
	    game.addPoints(pts);
	}
	
	
	public boolean interactWith(GameItem other) {
		
		boolean success = false;
	    boolean canInteract = other.isInPosition(this.pos); 
	    Position topPos = this.pos.translate(new Position(0, -1));
	    if (canInteract || (other.isInPosition(topPos)))
	        success = other.receiveInteraction(this);         
	    
	    return canInteract && success;
	    
	}
	
	@Override
	public boolean receiveInteraction(Goomba goomba) {
		
		if (big) {
			
	        this.setBig(false);
	        return true;
		 
	    } 
		
		else 
	   		this.muere();
	    
	    return true;    
	}
	
	@Override
	public boolean receiveInteraction(Land land) {
	    return false; 
	}

	@Override
	public boolean receiveInteraction(Exit_door exit_door) {		
		
	    game.marioExited();
	    return true;
	    
	}

	@Override
	public boolean receiveInteraction(Mario mario) {
	    return false;
	}


	@Override
	public void update() {
		
	    if (!isAlive()) 
	    		return;
	    	    
		boolean ActionExecute = processActions(actions());		
		if(!ActionExecute) {
			
			super.update();
			if(!isAlive() && isFalling())
				muere();
			
		}
		
	}

	private boolean processActions(Iterable<Action> actions) {
	
	    boolean moved = false;	    	    

	    int maxH = 0, maxU = 0; //cont para mov horiz, y mov up

	    for (Action act : actions) {

	    	if(!isAlive()) 
	    		return moved;
	    	
	    	Position below = pos.translate(new Position(0, 1));  
  	    if (game.isInside(below) && game.isSolid(below))
  	    		setFalling(false);
  	    
  	    else 
  	    		setFalling(true); 	  	
  	    
 	    
        Position next = pos.translateMario(act);

        // Movimiento horizontal
        if (act == Action.LEFT || act == Action.RIGHT || act == Action.STOP) {
        	
            if (act == Action.LEFT) {
            	
	            	setDirx(-1);
	            	cambiarIconIzq();
	            	if(maxH > 3) continue;
	            	maxH++;   
            	
            }
            
            else if (act == Action.RIGHT) { 
            	
	            	setDirx(1);
	            	cambiarIconDer();
	            	if(maxH > 3) continue;
	            	maxH++;
	            	
            }
            
            else if (act == Action.STOP) {
            	
	            	setDirx(0);
	            	icon = Messages.MARIO_STOP;
            	
            }
            if (canMoveTo(next)) {
            	
                pos = next;	               
                moved = true;
                
            }
    
        }

        if (act == Action.UP) {

            if (game.isInside(next) && game.isEmpty(next)) {
	            if(canMoveTo(next)) {    
	            		
	            	if(maxU > 3) continue;
	            	
	            	maxU++;
	            	pos = next;
	            	setFalling(true);
	            	moved = true;  	            		
	            }            	
            }
            
        }

        // Caída rápida
        if (act == Action.DOWN) {

            if (isFalling()) {
            	
                while (game.isInside(below) && !game.isSolid(below)) {
                	
                    pos = below;
        				game.doInteraction(this);
                    below = pos.translateMario(act);
                    if (!game.isInside(below))                     	
                        return false;                   
                }
                
                moved = true;

            } 
            else {
                setDirx(0);
                icon = Messages.MARIO_STOP;	                 
            }
            
        }
        
        if(act != Action.DOWN)
        		game.doInteraction(this);
    
    }
	    
	    return moved;
	    
	}
	
	public Iterable<Action> actions() {			    	
		return actionList.IterableAndClear();	        
	}
	
	public void addAction(Action act) {
		actionList.add(act);
	}

	@Override
	public boolean receiveInteraction(Box obj) {
		return false;
	}

	@Override
	public boolean receiveInteraction(Mushroom obj) {
		
		if(!big) {
			setBig(true);
		}	

		return true;
	}
	
	//En prueba
	@Override
    protected int getAllowedArgs() {
        return AllowedArgsGameObject;
    }
	
//	@Override
//    public GameObject parse(String[] objWords, GameWorld game) throws ObjectParseException, OffBoardException {
//        
//        // 1. Dejar que MovingObject parsee la dirección
//        GameObject obj = super.parse(objWords, game);
//        
//        if (obj == null) return null;
//        Mario m = new Mario(game, pos);
//        Mario m = (Mario) obj;
//
//        // 2. Si hay 4ª palabra, es el TAMAÑO (Small/Big/Super)
//        if (objWords.length > 3) {
//        	
//            if (objWords[3].equalsIgnoreCase("BIG") || objWords[3].equalsIgnoreCase("B")) 
//        		m.setBig(true);
//
//            else if (objWords[3].equalsIgnoreCase("SMALL") || objWords[3].equalsIgnoreCase("S")) 
//        		m.setBig(false);	
//            else 
//            		throw new ObjectParseException(Messages.ERROR_INVALID_MARIO_SIZE.formatted(String.join(" ", objWords)));
//        }
//        
//        
//        return m;
//    }
	@Override
	public Mario parse(String[] objWords, GameWorld game) throws OffBoardException, ObjectParseException {

//		if(!objWords[1].equalsIgnoreCase(NAME) && !objWords[1].equalsIgnoreCase(SHORTCUT)) 
//			return null; //esto no se si está bien
		
		GameObject obj = super.parse(objWords, game);
		
		if (obj == null) 
	        return null;
	    
		
	//    Mario m = new Mario(game, pos);
		Mario m = (Mario) obj;

	    // Dirección

//	    if (objWords.length > 2) {
//
//	        if (objWords[2].equalsIgnoreCase("RIGHT") || objWords[2].equalsIgnoreCase("R")) 
//	        		m.setDirx(1);
//
//	        else if (objWords[2].equalsIgnoreCase("LEFT") || objWords[2].equalsIgnoreCase("L")) {
//	        	
//	        	m.setDirx(-1);
//	        	m.cambiarIconIzq();
//	        	
//	        }
//	        
//	    }
		
	    // Tamaño

	    if (objWords.length > 3) {

	        if (objWords[3].equalsIgnoreCase("BIG") || objWords[3].equalsIgnoreCase("B")) 
	        		m.setBig(true);

	        else if (objWords[3].equalsIgnoreCase("SMALL") || objWords[3].equalsIgnoreCase("S")) 
	        		m.setBig(false);	
	        else 
	        	throw new ObjectParseException(Messages.ERROR_INVALID_MARIO_SIZE.formatted(String.join(" ", objWords)));
	    }
	    return m;
	}

	public boolean isDirectlyBelow(Position pos) {
		
		Position below = pos.translate(new Position(0, 1));
		return this.isInPosition(below);
		
	}
	
	@Override
    public void receiveAction(Action act) {
        addAction(act);
    }

	@Override
	public String toString() {
	    return this.icon;
	}

	@Override
	protected GameObject createObject(GameWorld game, Position pos, String[] s) {
		return new Mario(game, pos);
//		return null;
	}			
	
	@Override
    public String stringify() {
        // Llama a MovingObject.getGameItemState (que ya pone Posición, Nombre y Dirección)
        StringBuilder sb = new StringBuilder(super.stringify());
        
        if (big) {
            sb.append(" BIG");
        } else {
            sb.append(" SMALL");
        }
        return sb.toString();
    }
}