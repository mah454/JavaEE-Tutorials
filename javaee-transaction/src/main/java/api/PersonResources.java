package api;

import bl.PersonManager;
import persistence.Person;

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
public class PersonResources {

    @EJB
    private PersonManager personManager;

    @Path("/add")
    @GET
    public Response addPerson(@QueryParam("name") @Valid @NotEmpty(message = "Please enter name") String name,
                              @QueryParam("family") @Valid @NotEmpty(message = "Please enter family") String family) {
        Person person = new Person(name, family);
        personManager.addPerson(person);
        return Response.ok(person).build();
    }

    @Path("/ex")
    @GET
    public Response exAddPerson(@QueryParam("name") @Valid @NotEmpty(message = "Please enter name") String name,
                                @QueryParam("family") @Valid @NotEmpty(message = "Please enter family") String family) {
        Person person = new Person(name, family);
        personManager.exceptionalAddPerson(person);
        return Response.ok(person).build();
    }

    @Path("/list")
    @GET
    public Response personList() {
        var people = personManager.personList();
        return Response.ok(people).build();
    }


}
