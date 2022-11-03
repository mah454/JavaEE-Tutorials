package ir.moke.javaee.api;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/realm")
public class RealmApi {

    @Path("/hello")
    @GET
    public Response sayHello() {
        return Response.ok("Hello dear !").build();
    }

    @Path("/auth")
    @GET
    @RolesAllowed("admin")
    public Response auth() {
        return Response.accepted("Access Granted ...").build();
    }
}
