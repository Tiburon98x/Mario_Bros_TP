//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.control.commands;

import java.util.Arrays;

import tp1.exception.CommandExecuteException;
import tp1.exception.CommandParseException;
import tp1.exception.GameModelException;
import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class AddObjectCommand extends AbstractCommand {

	private static final String NAME = Messages.COMMAND_ADDOBJECT_NAME;
	private static final String SHORTCUT = Messages.COMMAND_ADDOBJECT_SHORTCUT;
	private static final String DETAILS = Messages.COMMAND_ADDOBJECT_DETAILS;
	private static final String HELP = Messages.COMMAND_ADDOBJECT_HELP
			+ System.lineSeparator()
			+ "      <object_description> = (col,row) objName [dir [BIG|SMALL]]. Ej. (12,3) Mario LEFT SMALL";
	
	private String[] objWords; 
	private String objString;	
	
	public AddObjectCommand() {
        super(NAME, SHORTCUT, DETAILS, HELP);
    }

	
	public AddObjectCommand(String[] objectDescription) {
		super(NAME, SHORTCUT, DETAILS, HELP);
		this.objWords = objectDescription;
		this.objString = String.join(" ", objectDescription);;
	}
	
//	@Override
//	public void execute(GameModel game, GameView view) throws CommandExecuteException {
//		
//		if (game.addGameObject(objWords)) {
//	    	   
//			game.AddObject();
//			view.showGame();
//	           
//		} else 
//			//view.showError(Messages.INVALID_GAME_OBJECT.formatted(objString));
//			throw new CommandExecuteException(Messages.INVALID_GAME_OBJECT.formatted(objString));
//	       
//		}
	
	@Override
	public void execute(GameModel game, GameView view) throws CommandExecuteException {
		
        try {
		    game.addObject(objWords);
			game.AddObject();
			view.showGame();
        } catch (GameModelException e) {
        	
            throw new CommandExecuteException(Messages.ERROR_COMMAND_EXECUTE, e);
        }
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
		 
		if (matchCommandName(commandWords[0])) {
			if (commandWords.length < 2)
				throw new CommandParseException(Messages.INVALID_COMMAND_PARAMETERS); 

//				return null;
	           
			String[] objWords = Arrays.copyOfRange(commandWords, 1, commandWords.length);
			return new AddObjectCommand(objWords);
	           
		}
		return null;
	       
	} 
}