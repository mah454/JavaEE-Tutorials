package ir.moke.javaee.api;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/sample")
@DenyAll
public class Sample {

    @GET
    @Path("/hello")
    public Response sayHello() {
        return Response.ok("Hello dear !").build();
    }

    @GET
    @Path("/auth")
    @RolesAllowed("admin")
    public Response auth() {
        return Response.accepted("Auth Success ...").build();
    }

    @GET
    @Path("/{name}")
    @RolesAllowed("admin")
    public Response sayMyName(@PathParam("name") String name) {
        System.out.println("Work ...");
        return Response.ok("Hello " + name).build();
    }
}
