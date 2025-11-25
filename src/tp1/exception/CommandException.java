package tp1.exception;

public class CommandException extends Exception {

	private static final long serialVersionUID = 1L;

	public CommandException(String message) {
		super(message);
	}

	public CommandException(String message, Throwable error) {
		super(message, error);
	}	

}
