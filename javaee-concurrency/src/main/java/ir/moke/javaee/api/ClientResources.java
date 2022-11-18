package ir.moke.javaee.api;

import ir.moke.javaee.Counter;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/counter")
@Stateless
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class ClientResources {

    @EJB
    private Counter counter;

    @GET
    @Path("/add")
    public Response add() {
        counter.setCount();
        return Response.ok().build();
    }

    @GET
    @Path("/read")
    public Response updateClient() {
        return Response.ok(counter.readCount()).build();
    }
}
