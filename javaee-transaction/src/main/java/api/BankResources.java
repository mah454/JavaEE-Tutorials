package api;

import bl.BankBusiness;
import persistence.enity.Client;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Stateless
public class BankResources {

    @EJB
    private BankBusiness bankBusiness;

    @Path("/add")
    @GET
    public Response addClient(@QueryParam("name") @Valid @NotEmpty(message = "Please enter name") String name,
                              @QueryParam("family") @Valid @NotEmpty(message = "Please enter family") String family) {
        Client client = new Client(name, family);
        bankBusiness.openAccount(client);
        return Response.ok(client).build();
    }

    @Path("/ex")
    @GET
    public Response exAddClient(@QueryParam("name") @Valid @NotEmpty(message = "Please enter name") String name,
                                @QueryParam("family") @Valid @NotEmpty(message = "Please enter family") String family) {
        Client client = new Client(name, family);
        bankBusiness.exceptionalAddClient(client);
        return Response.ok(client).build();
    }

    @Path("/list")
    @GET
    public Response personList() {
        var people = bankBusiness.personList();
        return Response.ok(people).build();
    }


}
