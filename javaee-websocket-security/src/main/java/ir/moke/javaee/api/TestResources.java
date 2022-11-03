package ir.moke.javaee.api;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/test")
public class TestResources {

    @GET
    @Path("/hello")
    @RolesAllowed("restApi")
    public String sayHello() {
        return "Hello dear ... !";
    }

    @GET
    @Path("/bye")
    public String sayBye() {
        return "Bye bye !";
    }
}
