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
		
		super(game, pos, NAME, SHORTCUT);
		this.icon = Messages.MARIO_RIGHT;
		setFalling(false);
		setDirx(1);
		this.actionList = new ActionList();
		big = true;

	}
	
	public Mario() {
		super(NAME, SHORTCUT);
		
	}
	
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
	    Position topPosBig = this.pos.translate(new Position(0, -2));

	    if (canInteract || (other.isInPosition(topPos)) || (this.big && other.isInPosition(topPosBig) && other.isSolid())) {
	      //la tercera condición sirve para detectar el box y hacer la interacción 
	    	
	    	success = other.receiveInteraction(this);     
	    }
	    
	    
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
	    	    
		boolean ActionExecute = processActions(actions());	//procesamos las acciones, si procesa alguno, no hay que hacer update 
		
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
	    	
	    //Revisamos si está en el aire
	    Position below = pos.translate(new Position(0, 1));  
  	    if (game.isInside(below) && game.isSolid(below))
  	   		setFalling(false);
  	    
  	    else 
  	    	setFalling(true); 	  	
  	     	    
        Position next = pos.translateMario(act); //siguiente posición

        // Movimiento horizontal
        if (act == Action.LEFT || act == Action.RIGHT || act == Action.STOP) {
        	
            if (act == Action.LEFT) {
            	
	            	setDirx(-1);
	            	cambiarIconIzq(); //icon Mario moviendose a la izq
	            	if(maxH > 3) continue;
	            	maxH++;   
            	
            }
            
            else if (act == Action.RIGHT) { 
            	
	            	setDirx(1);
	            	cambiarIconDer(); //icon Mario moviendose a la der
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
	            	setFalling(true); //necesario para las interacciones
	            	moved = true;  	            		
	            }            	
            }
            
        }

        // Caída rápida
        if (act == Action.DOWN) {

            if (isFalling()) {
            	
                while (game.isInside(below) && !game.isSolid(below)) {
                	
                    pos = below;
        			game.doInteraction(this); //debes chequear en cada bajada
                    below = pos.translateMario(act);
                    
                }
                if (!game.isInside(below)) {                    	
                	muere();
                	return true;
                }
                                      
                moved = true;
            } 
            else {
                setDirx(0);
                icon = Messages.MARIO_STOP;	                 
            }           
        }
        
        if(act != Action.DOWN) //DOWN ya chequea las interacciones 
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
	
	@Override
    protected int getAllowedArgs() {
        return AllowedArgsGameObject;
    }
	
	@Override
	public Mario parse(String[] objWords, GameWorld game) throws OffBoardException, ObjectParseException {
		
		GameObject obj = super.parse(objWords, game);
		
		if (obj == null) 
	        return null;
	    
		Mario m = (Mario) obj;

	    // Tamaño

	    if (objWords.length > 3) {

	        if (objWords[3].equalsIgnoreCase(Messages.OBJECT_MARIO_BIG) || objWords[3].equalsIgnoreCase(Messages.OBJECT_MARIO_BIG_SHORTCUT)) 
	        		m.setBig(true);

	        else if (objWords[3].equalsIgnoreCase(Messages.OBJECT_MARIO_SMALL) || objWords[3].equalsIgnoreCase(Messages.OBJECT_MARIO_SMALL_SHORTCUT)) 
	        		m.setBig(false);	
	        else 
	        	throw new ObjectParseException(Messages.ERROR_INVALID_MARIO_SIZE.formatted(String.join(" ", objWords)));
	    }
	    return m;
	}

	public boolean isDirectlyBelow(Position pos) { //Método para BOX
			
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
	}			
	
	@Override
    public String stringify() {
		
        StringBuilder sb = new StringBuilder(super.stringify());
        
        if (big) 
            sb.append(" " + Messages.OBJECT_MARIO_BIG);
        else 
            sb.append(" " + Messages.OBJECT_MARIO_SMALL);
        
        return sb.toString();
    }
}