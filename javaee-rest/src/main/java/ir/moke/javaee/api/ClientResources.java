package ir.moke.javaee.api;

import ir.moke.javaee.crud.ClientCRUD;
import ir.moke.javaee.entity.Client;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/client")
@Stateless
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class ClientResources {

    @Inject
    private ClientCRUD clientCRUD;

    @POST
    @Path("/register")
    public Response registerClient(Client client) {
        clientCRUD.save(client);
        return Response.ok(client).build();
    }

    @PUT
    @Path("/update")
    public Response updateClient(Client client) {
        clientCRUD.update(client);
        return Response.ok(client).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response removeClient(@PathParam("id") long id) {
        clientCRUD.remove(id);
        return Response.ok().build();
    }

    @GET
    @Path("/find")
    public Response findClients(@QueryParam("id") Long id) {
        if (id != null) {
            return Response.ok(clientCRUD.find(id)).build();
        } else {
            return Response.ok(clientCRUD.find()).build();
        }
    }
}
