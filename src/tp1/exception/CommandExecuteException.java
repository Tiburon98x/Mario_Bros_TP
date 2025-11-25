package tp1.exception;

public class CommandExecuteException extends CommandException {


	private static final long serialVersionUID = 1L;

	
	public CommandExecuteException(String message) {	
		super(message);
	}
	
	public CommandExecuteException(String message, Throwable error) {	
		super(message, error);
	}
	
}
