package ir.moke.javaee.api;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

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
