package tp1.logic.gameobjects;

import tp1.exception.ObjectParseException;
import tp1.exception.OffBoardException;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class Box extends GameObject{

	private static final String NAME = Messages.OBJECT_BOX;
    private static final String SHORTCUT = Messages.OBJECT_BOX_SHORTCUT;
    
    private boolean full = true;
	private String icon;
	private static final int AllowedArgsBox = 3;
	
	public Box(GameWorld game, Position pos) {
		
//		super(game, pos);
		super(game, pos, NAME, SHORTCUT);
		this.full = true;
		this.icon = Messages.BOX;
		
		
	}

	public Box() {
		super(NAME, SHORTCUT);
		//super();
	}
	
	@Override
	public int getAllowedArgs() {
        return AllowedArgsBox;
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
	public GameObject createObject(GameWorld game, Position pos, String[] s) {
		//Hay que arreglar cuando se quiere añadir una caja vacía porque añade siempre una llena
		//Mi idea era crear otro constructor al que se le paso como parámetro como está la caja
		//Mejor idea imitar el mario creas y luego haces un setState dependiendo si esta vacía 
		// o llena pasándolo por los parámetros
		Box b = new Box(game, pos);
		return b;
	}

	
	public void setState(boolean state){
		
		this.full = state;
		if (full) 
	        this.icon = Messages.BOX;    
	     else 
	        this.icon = Messages.EMPTY_BOX; 
	    
	}
	
	@Override
	public Box parse(String[] objWords, GameWorld game) throws OffBoardException, ObjectParseException {
		
		GameObject obj = super.parse(objWords, game);
		if (obj == null) 
	        return null;
	    
		Box b = (Box) obj; //Casting necesario

	    if (objWords.length > 2) {

	        if (objWords[2].equalsIgnoreCase("FULL")) 
	        		b.setState(true);

	        else if (objWords[2].equalsIgnoreCase("EMPTY")) 
	        		b.setState(false);	
	        else 
	        	throw new ObjectParseException(Messages.INVALID_BOX_STATUS.formatted(String.join(" ", objWords)));
	    }
	    
	    return b;
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

	@Override
	public boolean receiveInteraction(Mario mario) {
		

		if (mario.isDirectlyBelow(this.pos) && Messages.BOX.equals(this.icon)) {
			mario.givePointsToGame(50);
			setState(false); //Empty BOX
			Mushroom obj = new Mushroom(game, this.pos.translate(new Position(0, -1)));
			game.addMushroom(obj);
			return true;
	     
		}
	   
		return false;
	   
	}

	@Override
	public boolean receiveInteraction(Goomba obj) {
		return false;
	}

	@Override
	public String toString() {
	    return this.icon;
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
	
	@Override
    public String stringify() {
        StringBuilder sb = new StringBuilder(super.stringify());

        if (this.icon.equals(Messages.EMPTY_BOX)) { //comprobamos si es vacia para indicarlo
             sb.append(" Empty ");
        }
        return sb.toString();
    }

}