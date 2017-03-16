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
     * @param msg the detail message.
     */
    public TheException(String msg) {
        super(msg);
    }

    private ErrorMessage errorMessage;

    public TheException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

}
