
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
			//TODO fill with your code
			// new UpdateCommand(),
			new HelpCommand(),
			new ExitCommand()
	);

	public static Command parse(String[] commandWords) {		
		for (Command c: availableCommands) {
			//TODO fill with your code
		}
		return null;
	}
		
	public static String commandHelp() {
		StringBuilder commands = new StringBuilder();
		
		commands.append(Messages.HELP_AVAILABLE_COMMANDS).append(Messages.LINE_SEPARATOR);
		
		for (Command c: availableCommands) {
			
			c.helpText();
			//TODO fill with your code
		}


		return commands.toString();
	}

}
