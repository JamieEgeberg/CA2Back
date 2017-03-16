package exception;

import com.google.gson.Gson;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by Niki on 2017-03-16.
 *
 * @author Niki
 */
@Provider
public class TheExceptionMapper implements ExceptionMapper<TheException> {

    private static Gson gson = new Gson();

    @Override
    public Response toResponse(TheException ex) {
        if (ex.getErrorMessage() != null)
            return Response
                    .status(ex.getErrorMessage().getCode())
                    .header("Content-Type", "application/json")
                    .entity(gson.toJson(ex.getErrorMessage()))
                    .build();

        // if ex.getErrorMessage is null, then just return the message as string
        return Response
                .status(500)
                .entity(ex.getMessage())
                .build();
    }
}
