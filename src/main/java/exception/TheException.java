package exception;

/**
 * Created by Niki on 2017-03-16.
 *
 * @author Niki
 */
public class TheException extends Exception {
    /**
     * Creates a new instance of <code>TheException</code> without detail
     * message.
     */
    public TheException() {
    }

    /**
     * Constructs an instance of <code>TheException</code> with the specified
     * detail message.
     *
     * @param message the detail message.
     */
    public TheException(String message) {
        super(message);
    }

    private ErrorMessage errorMessage;

    public TheException(int code, boolean debug) {
        errorMessage = new ErrorMessage(this, super.getMessage(), code, debug);
    }

    public TheException(String message, int code, boolean debug) {
        super(message);
        errorMessage = new ErrorMessage(this, message,  code, debug);
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }
}
