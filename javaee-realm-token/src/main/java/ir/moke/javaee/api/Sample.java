package ir.moke.javaee.api;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

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
