//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.control.commands;

import tp1.exception.ActionParseException;
import tp1.exception.CommandExecuteException;
import tp1.exception.CommandParseException;
import tp1.logic.Action;
import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class ActionCommand extends AbstractCommand {

	private static final String NAME = Messages.COMMAND_ACTION_NAME;
    private static final String SHORTCUT = Messages.COMMAND_ACTION_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_ACTION_DETAILS;
    private static final String HELP = Messages.COMMAND_ACTION_HELP;

	private String[] words;
	
	public ActionCommand() {
        super(NAME, SHORTCUT, DETAILS, HELP);
    }
	
    public ActionCommand(String[] words) {
		super(NAME, SHORTCUT, DETAILS, HELP);
		this.words = words;
	}

//    @Override
//    public void execute(GameModel game, GameView view) throws CommandExecuteException{
//    	//try catch aqui
//        for (String s : words) {
//            Action act = parseAction(s);
//
//            if (act == null) {
//                view.showError(Messages.UNKNOWN_COMMAND.formatted(s));
//                return;
//            }
//            
//            game.addActionToMario(act);
//        }
//
//        game.update();
//        view.showGame();
//    }
    
    
    @Override
    public void execute(GameModel game, GameView view) throws CommandExecuteException {
    		
 //   	String forError = String.join(" ", words);
    	boolean emptyActions = true;
    	
    	for (String s : words) {
    		try {
    			Action act = Action.parseAction(s);
    			game.addActionToMario(act);
    			emptyActions = false;
            
    		} catch (ActionParseException e) {
            	
    			//Cuando le pasas la causa lo hace dos veces por como esta configurado el run de controller
    		//	throw new CommandExecuteException(Messages.UNKNOWN_COMMAND.formatted(NAME + " " + forError));
    		}
    	}
    	if(emptyActions)
    		throw new CommandExecuteException(Messages.EMPTY_ACTIONS);
    	
    	game.update();
    	view.showGame();
    }
    
	
//	@Override
//	public Command parse(String[] words){
//		
//	    if (!matchCommandName(words[0])) {
//	        return null; 
//	    }
//	    if (words.length < 2) 
//	        return null; 
//	    
//	    String[] parameters = new String[words.length - 1];
//
//	    // Copiamos las acciones desde words a parameters
//	    for (int i = 1; i < words.length; i++) {
//	        parameters[i - 1] = words[i];
//	    }
//
//	    return new ActionCommand(parameters);
//	}
    
    @Override
	public Command parse(String[] words) throws CommandParseException{
		
		if (matchCommandName(words[0]) && words.length == 1) 
			throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER); 
			    
//		if (words.length < 2) 
//			throw new CommandParseException(Messages.INVALID_COMMAND_PARAMETERS); 
		
		if (!matchCommandName(words[0]))
			return null;

		
			  
		String[] parameters = new String[words.length - 1];
	
		// Copiamos las acciones desde words a parameters
		for (int i = 1; i < words.length; i++) 
			parameters[i - 1] = words[i];
			    
	
		return new ActionCommand(parameters);
    	
    	   
	}
	
  
}
