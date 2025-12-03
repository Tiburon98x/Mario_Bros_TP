//GRUPO 23: YANG LI YANG, SALVADOR VALENZUELA MATOS

package tp1.exception;

public class GameModelException extends Exception {
	
    private static final long serialVersionUID = 1L;

    public GameModelException(String message) {
        super(message);
    }

    public GameModelException(String message, Throwable cause) {
        super(message, cause);
    }
}