//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.control.commands;

import tp1.exception.CommandParseException;

import tp1.view.Messages;



public abstract class NoParamsCommand extends AbstractCommand {

	public NoParamsCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);
	}

//	@Override
//	public Command parse(String[] commandWords){
//		
//		 if (commandWords.length == 1 && matchCommandName(commandWords[0]))
//		        return this; 
//		return null;
//		
//	}	
	 public Command parse(String[] commandWords) throws CommandParseException {
		 	if (commandWords.length > 1 && matchCommandName(commandWords[0]))
		 		throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		 	
		 	Command cmd = null;
		 	if (commandWords.length == 1 && matchCommandName(commandWords[0]))
		 		cmd = this;
		 	
		 	return cmd;
		 }
}