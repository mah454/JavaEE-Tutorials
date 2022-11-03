package ir.moke.javaee.api;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.json.JsonObject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

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
