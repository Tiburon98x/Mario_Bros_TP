package tp1.exception;

public class ActionParseException extends GameParseException {

	private static final long serialVersionUID = 1L;

    public ActionParseException(String message) {
        super(message);
    }
    
    public ActionParseException(String message, Throwable cause) {
        super(message, cause);
    }
	
}
