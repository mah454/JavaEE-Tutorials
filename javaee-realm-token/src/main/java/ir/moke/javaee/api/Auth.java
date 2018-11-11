package ir.moke.javaee.api;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@Path("/auth")
public class Auth {

    private static final Logger LOGGER = Logger.getLogger(Auth.class.getName());

    @Inject
    private SecurityContext context;

    @GET
    @Path("/login")
    public Response login() {
        LOGGER.log(Level.INFO, "login");
        if (context.getCallerPrincipal() != null) {
            JsonObject result = Json.createObjectBuilder()
                    .add("user", context.getCallerPrincipal().getName())
                    .build();
            return Response.ok(result).build();
        }
        return Response.status(UNAUTHORIZED).build();
    }
}
