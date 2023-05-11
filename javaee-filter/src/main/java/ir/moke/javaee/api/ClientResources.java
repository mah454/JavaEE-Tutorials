package ir.moke.javaee.api;

import ir.moke.javaee.PersonDTO;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/test")
@Stateless
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class ClientResources {

    @POST
    public Response registerClient(PersonDTO dto) {
        System.out.println("Body received: " + dto);
        return Response.ok("Salam").build();
    }
}
