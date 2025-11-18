//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.control.commands;

import java.util.Arrays;

import java.util.List;

import tp1.view.Messages;

public class CommandGenerator {
	
	private static final List<Command> availableCommands = Arrays.asList(
			new AddObjectCommand(new String[0]),
	        new ActionCommand(null),
			new UpdateCommand(),
	        new ResetCommand(0),
	        new HelpCommand(),
	        new ExitCommand()
	);
	

	
	public static Command parse(String[] commandWords) {	

		for (Command c: availableCommands) {
			
			Command parsedCommand = c.parse(commandWords); //llama al de NoParamsCommand
			 if (parsedCommand != null)  
		            return parsedCommand;
			
		}
		
		return null;
		
	}
		
	public static String commandHelp() {
		
		StringBuilder commands = new StringBuilder();	
		commands.append(Messages.HELP_AVAILABLE_COMMANDS).append(Messages.LINE_SEPARATOR);
		for (Command c: availableCommands)			
			commands.append(c.helpText());
		return commands.toString();
		
	}
	
	
	
}
