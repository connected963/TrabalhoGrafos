package exceptions;

/**
 * Created by connected on 11/15/16.
 */
public class GraphInvalidFormatException extends  RuntimeException {

    public GraphInvalidFormatException() {
        super();
    }

    public GraphInvalidFormatException(String message) {
        super(message);
    }

    public GraphInvalidFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public GraphInvalidFormatException(Throwable cause) {
        super(cause);
    }

}
