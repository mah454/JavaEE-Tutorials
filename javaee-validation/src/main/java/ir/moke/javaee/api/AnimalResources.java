package ir.moke.javaee.api;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/animal")
@RequestScoped
public class AnimalResources {

    @Inject
    private Event<JsonObject> event;

    @POST
    @Path("/new")
    @Produces("application/json")
    @Consumes("application/json")
    public Response getDogByJson(JsonObject jsonObject) {
        event.fire(jsonObject);
        return Response.ok("ok").build();
    }
}
