package tp1.control.commands;

import tp1.logic.Action;
import tp1.logic.ActionList;
import tp1.logic.Game;
import tp1.view.GameView;
import tp1.view.Messages;

public class ActionCommand extends NoParamsCommand {
	//realmente no extiende de NOParamsCommand porque si necesita parametros

	private static final String NAME = Messages.COMMAND_ACTION_NAME;
    private static final String SHORTCUT = Messages.COMMAND_ACTION_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_ACTION_DETAILS;
    private static final String HELP = Messages.COMMAND_ACTION_HELP;
	//HAY QUE EDITAR EL MESSAGE PARA EL RESET CREO

	private String[] words;

	
    public ActionCommand(String[] words) {
		super(NAME, SHORTCUT, DETAILS, HELP);
		this.words = words;
	}

    @Override
    public void execute(Game game, GameView view) {

        for (String s : words) {
            Action act = parseAction(s);

            if (act == null) {
                view.showError("Unknown action: " + s);
                return;
            }
            if(!game.getActionList().add(act)) {
            	
            	//rellenar
            }
            boolean added = game.getActionList().add(act);
            if (!added) {
                view.showError("Could not add action: " + s);
                return;
            }
        }

        view.showMessage("Actions queued.");
    }
	
	@Override
	public Command parse(String[] words) {
	    if (!matchCommandName(words[0])) 
	        return null;  // No es el comando "action"

	    if (words.length < 2) 
	        return null;  // No hay acciones

	    // Creamos un nuevo array para las acciones
	    String[] parameters = new String[words.length - 1];

	    // Copiamos manualmente las acciones desde words a parameters
	    for (int i = 1; i < words.length; i++) {
	        parameters[i - 1] = words[i];
	    }

	    // Creamos un ActionCommand con esas acciones
	    return new ActionCommand(parameters);
	}
	
    private Action parseAction(String str) {
    	
        str = str.toLowerCase();
        switch (str) {
            case "l":
            case "left":
                return Action.LEFT;
            case "r":
            case "right":
                return Action.RIGHT;
            case "u":
            case "up":
                return Action.UP;
            case "d":
            case "down":
                return Action.DOWN;
            case "s":
            case "stop":
                return Action.STOP;
            default:
                return null;
                
        }
    }
	
}
