package tp1.exception;

public class PositionParseException extends GameParseException {

	private static final long serialVersionUID = 1L;

    public PositionParseException(String message) {
        super(message);
    }
    
    public PositionParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
