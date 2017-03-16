package exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Niki on 2017-03-16.
 *
 * @author Niki
 */
public class ErrorMessage {

    private int code;
    private String message;
    private String stackTrace;

    public ErrorMessage(Throwable ex, String message, int code, boolean debug) {
        this.code = code;
        this.message = message;

        if (!debug) return;
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        this.stackTrace = sw.toString();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

}
