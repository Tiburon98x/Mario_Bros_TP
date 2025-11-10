
package tp1.control.commands;

import java.util.Arrays;

import java.util.List;

import tp1.view.*;

public class CommandGenerator {

//	private GameView view;
//	
//	public CommandGenerator(GameView view) {
//		
//		this.view = view;	
//	}
	
	private static final List<Command> availableCommands = Arrays.asList(
	        new ActionCommand(null),
			new UpdateCommand(),
	        new ResetCommand(0),
	        new HelpCommand(),
	        new ExitCommand()
	);

	public static Command parse(String[] commandWords) {	

		for (Command c: availableCommands) {

			Command parsedCommand = c.parse(commandWords); //llama al de NoParamsCommand
			 if (parsedCommand != null) { //aquí no se muy bien si cuando es NULL es hacer el movimiento automatico (m.u.)
		            return parsedCommand;
			 }
		}		
		return null; //respecto al comentario anterior, capaz en algun sitio haya q hacer if(parsedCommand = null) haga update con m.u.
	}
		
	public static String commandHelp() {
		StringBuilder commands = new StringBuilder();
		
		commands.append(Messages.HELP_AVAILABLE_COMMANDS).append(Messages.LINE_SEPARATOR);
		
		for (Command c: availableCommands) {
			
			commands.append(c.helpText());
		}
		return commands.toString();
	}

}
