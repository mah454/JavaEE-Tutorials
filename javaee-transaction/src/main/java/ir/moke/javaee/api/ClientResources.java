package ir.moke.javaee.api;

import ir.moke.javaee.bl.BusinessClient;
import ir.moke.javaee.persistence.enity.Client;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/client")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Stateless
public class ClientResources {

    @EJB(beanName = "cmt")
    private BusinessClient cmtClient;

    @EJB(beanName = "bmt")
    private BusinessClient bmtClient;


    @POST
    @Path("/cmt/add")
    public Response addCmtClient(Client client) {
        cmtClient.openAccount(client);
        return Response.ok(client).build();
    }

    @POST
    @Path("/bmt/add")
    public Response addBmtClient(Client client) {
        bmtClient.openAccount(client);
        return Response.ok(client).build();
    }

    @Path("/list")
    @GET
    public Response personList() {
        var people = cmtClient.personList();
        return Response.ok(people).build();
    }
}
