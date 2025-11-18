package tp1.control.commands;

import java.util.Arrays;

import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class AddObjectCommand extends AbstractCommand {

	private static final String NAME = "addObject";
	private static final String SHORTCUT = "aO";
	private static final String DETAILS = "[a]dd[O]bject <object_description>";
	private static final String HELP = "adds to the board the object given by object_description."
			+ System.lineSeparator()
			+ "      <object_description> = (col,row) objName [dir [BIG|SMALL]]. Ej. (12,3) Mario LEFT SMALL";
	
	private String[] objWords; 
	private String objString;	
	
	
	
	public AddObjectCommand(String[] objectDescription) {
		super(NAME, SHORTCUT, DETAILS, HELP);
		this.objWords = objectDescription;
		this.objString = String.join(" ", objectDescription);;
	}
	
	 @Override
	   public void execute(GameModel game, GameView view) {
		 
	       if (game.addGameObject(objWords)) {
	    	   
	           game.AddObject();
	           view.showGame();
	           
	       } else 
	           view.showError(Messages.INVALID_GAME_OBJECT.formatted(objString));
	       
	   }

	 @Override
	   public Command parse(String[] commandWords) {
		 
	       if (matchCommandName(commandWords[0])) {
	           if (commandWords.length < 2)
	               return null;
	           
	           String[] objWords = Arrays.copyOfRange(commandWords, 1, commandWords.length);
	           return new AddObjectCommand(objWords);
	           
	       }
	       return null;
	       
	   }

	 
	 
}