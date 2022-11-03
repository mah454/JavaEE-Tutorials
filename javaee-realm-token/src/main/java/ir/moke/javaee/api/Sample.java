package ir.moke.javaee.api;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/sample")
public class Sample {

    @Inject
    private SecurityContext context;

    @Path("/hello")
    @GET
    @RolesAllowed("admin")
    public String sayHello() {
        return "Hello dear ...";
    }

    @Path("/bye")
    @GET
    public String sayBye() {
        return "Bye bye !";
    }
}
