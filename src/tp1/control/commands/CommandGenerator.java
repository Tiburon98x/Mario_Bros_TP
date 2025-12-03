//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.control.commands;

import java.util.Arrays;

import java.util.List;

import tp1.exception.CommandParseException;
import tp1.view.Messages;

public class CommandGenerator {
	
	private static final List<Command> availableCommands = Arrays.asList(
			
			//cada Comando tendrá su constructor vacío. En el parseo se devuelve el
			//comando con sus args correspondientes
	        new LoadCommand(),
			new SaveCommand(),
			new AddObjectCommand(),
	        new ActionCommand(),
			new UpdateCommand(),
	        new ResetCommand(),
	        new HelpCommand(),
	        new ExitCommand()	          
	);
	
	public static Command parse(String[] commandWords) throws CommandParseException{	

		for (Command c: availableCommands) {
			
			Command parsedCommand = c.parse(commandWords); //llama a los parses correspondientes de cada comando
			if (parsedCommand != null)  
				return parsedCommand;
			//nulo = excepción, no nulo = comando bien parseado
		}	
		 throw new CommandParseException(Messages.UNKNOWN_COMMAND.formatted(commandWords[0]));
	}
		
	public static String commandHelp() {
		
		StringBuilder commands = new StringBuilder();	
		commands.append(Messages.HELP_AVAILABLE_COMMANDS).append(Messages.LINE_SEPARATOR);
		
		for (Command c: availableCommands)			
			commands.append(c.helpText());
		return commands.toString();
		
	}
}
