package exception;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Jamie
 */
@Provider
public class CompanyNotFoundMapper implements ExceptionMapper<CompanyNotFoundException> {

    static Gson gson = new Gson();
    @Context
    ServletContext context;

    @Override
    public Response toResponse(CompanyNotFoundException ex) {
        return Response.status(404).entity(ex.getMessage()).build();
    }

    //@Override
//    public Response toResponse(CompanyNotFoundException ex) {
//       boolean isDebug = context.getInitParameter("debug").equals("true");
//       ErrorMessage err = new ErrorMessage(ex,404,isDebug);
//       err.setDescription("You tried to call ...");
//       return Response.status(404)
//				.entity(gson.toJson(err))
//				.type(MediaType.APPLICATION_JSON).
//				build();
//	}
}
