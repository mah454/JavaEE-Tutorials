package ir.moke.javaee.api;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/test")
public class HelloWorld {

    @Path("/hello")
    @GET
    @RolesAllowed("admin")
    public Response sayHello() {
        return Response.ok("Hello World ...").build();
    }
}
