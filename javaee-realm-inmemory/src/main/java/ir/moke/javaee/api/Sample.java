package ir.moke.javaee.api;

import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Response;

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
