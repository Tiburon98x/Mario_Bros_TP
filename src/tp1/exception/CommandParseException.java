package tp1.exception;

public class CommandParseException extends CommandException {


	private static final long serialVersionUID = 1L;
	
	public CommandParseException(String message) {
		super(message);
	}

	public CommandParseException(String formatted, Throwable error) {
		super(formatted, error);
	}
	
	
	
}
