package ir.moke.javaee.api;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@Path("/auth")
@RequestScoped
public class AuthResource {

    @Inject
    private SecurityContext context;

    @GET
    @Path("/login")
    public Response login() {
        if (context.getCallerPrincipal() != null) {
            JsonObject result = Json.createObjectBuilder()
                    .add("user", context.getCallerPrincipal().getName())
                    .build();
            return Response.ok(result).build();
        }
        return Response.status(UNAUTHORIZED).build();
    }
}
