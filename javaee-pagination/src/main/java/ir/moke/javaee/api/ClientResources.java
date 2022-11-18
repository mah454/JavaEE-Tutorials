package ir.moke.javaee.api;

import ir.moke.javaee.crud.ClientCRUD;
import ir.moke.javaee.entity.Client;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/client")
@Stateless
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class ClientResources {

    @Inject
    private ClientCRUD clientCRUD;

    @GET
    @Path("/find")
    public Response findClients(@QueryParam("id") Long id,
                                @QueryParam("id") String name,
                                @QueryParam("id") String family,
                                @BeanParam Pagination pagination) {
        List<Client> clients = clientCRUD.find(id, name, family, pagination);
        return Response.ok().entity(clients).build();
    }
}
