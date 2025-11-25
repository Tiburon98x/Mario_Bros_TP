package tp1.exception;


public class OffBoardException extends GameModelException {
	
    private static final long serialVersionUID = 1L;

    public OffBoardException(String message) {
        super(message);
    }
    
    public OffBoardException(String message, Throwable error) {
        super(message, error);
    }
    
}