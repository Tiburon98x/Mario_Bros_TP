//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.control.commands;

import tp1.exception.CommandExecuteException;
import tp1.exception.CommandParseException;
import tp1.exception.GameModelException;
import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class ResetCommand extends AbstractCommand {
	
//	private static final int defaultLevel = -10;
	private boolean haslevel = false; //¿?
	private int level;
	
	private static final String NAME = Messages.COMMAND_RESET_NAME;
    private static final String SHORTCUT = Messages.COMMAND_RESET_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_RESET_DETAILS;
    private static final String HELP = Messages.COMMAND_RESET_HELP;   
    
    public ResetCommand() {
        super(NAME, SHORTCUT, DETAILS, HELP);
		this.haslevel = false;  

    }
    
	public ResetCommand(int level) {
		
		super(NAME, SHORTCUT, DETAILS, HELP);
		this.level = level;
		this.haslevel = true; 
		
	}
//	public void execute(GameModel game, GameView view) {
//
//
//		int targetLevel;
//
//
//		if (level == defaultLevel) {
//
//		targetLevel = game.getCurrentLevel();
//
//
//		} else {
//
//		targetLevel = level;
//
//		}
//
//		if(targetLevel > 10)
//
//		view.showError(Messages.INVALID_LEVEL_NUMBER);
//
//		else {
//
//		game.reset(targetLevel);
//
//		view.showGame();
//
//		}
//
//		}
	@Override
	public void execute(GameModel game, GameView view) throws CommandExecuteException{
	
	        if(!haslevel)
	        	level = game.getCurrentLevel();        	

	        try {
//	        	
//	        	if (haslevel) {
//	                game.reset(level);
//	            } else {
//	                game.reset();
//	            }
//	            view.showGame();
	        	
	        	
	            game.reset(level);
   	            view.showGame();    
	        } catch (GameModelException e) {
	            throw new CommandExecuteException(e.getMessage());
	        }
//	        game.reset(level);
//	        view.showGame();

//	    } catch (CommandExecuteException nfe) {
//	       
//	        view.showError(nfe.getMessage());
//	    }
	}

//	 @Override
//	 public Command parse(String[] commandWords) {
//	
//		 if (commandWords.length == 1 && matchCommandName(commandWords[0])) 
//	            return new ResetCommand(defaultLevel); //usamos -10 para indicar en el execute que es erroneo
//		 
//		 if (commandWords.length == 2 && matchCommandName(commandWords[0])) {
//
//			 int parsedLevel = Integer.parseInt(commandWords[1]);
//             return new ResetCommand(parsedLevel);
//             
//		 }
//		 
//		 return null;		 
//	 }	 
	
	
	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		
	    if (matchCommandName(commandWords[0])) {
	    	
	        if (commandWords.length > 2) {
	        	
	            throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
	            
	        } else if (commandWords.length == 1)
//	        	return new ResetCommand(defaultLevel); // No necesario, si no tiene nivel se le atribuye el constructor sin nivel
	        	//Ahora la pregunta es sería necesario inicializar level a algún valor 
	        	return new ResetCommand();

	        try {

	        	int parsedLevel = Integer.parseInt(commandWords[1]);
	            return new ResetCommand(parsedLevel);
	        } catch (NumberFormatException nfe) {

	            throw new CommandParseException(Messages.LEVEL_NOT_A_NUMBER_ERROR.formatted(commandWords[1]), nfe);
	        }
	    }
	    return null; 
	}
	
	
}