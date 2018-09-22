package ir.moke.javaee.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/sample")
public class Sample {

    @GET
    @Path("/hello")
    public Response sayHello() {
        return Response.ok("Hello dear !").build();
    }

    @GET
    @Path("/{name}")
    public Response sayMyName(@PathParam("name") String name) {
        return Response.ok("Hello " + name).build();
    }
}
