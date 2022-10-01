package ir.moke.javaee.api;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

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
