package ir.moke.javaee.api;

import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/auth")
public class Auth {

    private static final Logger LOGGER = Logger.getLogger(Auth.class.getName());

    @Inject
    private SecurityContext securityContext;

    @GET
    public Response login(@QueryParam("username") String username, @QueryParam("password") String password) {
        LOGGER.log(Level.INFO, "login");
        if (securityContext.getCallerPrincipal() != null) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
