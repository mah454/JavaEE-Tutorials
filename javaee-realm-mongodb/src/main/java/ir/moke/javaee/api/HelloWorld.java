package ir.moke.javaee.api;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/test")
public class HelloWorld {

    @Path("/hello")
    @GET
    @RolesAllowed("admin")
    public Response sayHello() {
        return Response.ok("Hello World ...").build();
    }
}
