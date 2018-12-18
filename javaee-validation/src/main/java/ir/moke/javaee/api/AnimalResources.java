package ir.moke.javaee.api;

import ir.moke.javaee.model.Animal;
import ir.moke.javaee.model.Dog;

import javax.enterprise.context.RequestScoped;
import javax.json.JsonObject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/animal")
@RequestScoped
public class AnimalResources {


    @POST
    @Path("/test")
    @Produces("application/json")
    @Consumes("application/json")
    public Response testAnimal(@Valid Animal animal) {
        System.out.println(animal);
        return Response.ok("ok").build();
    }

    @POST
    @Path("/json/dog")
    @Produces("application/json")
    @Consumes("application/json")
    public Response getDogByJson(@Valid JsonObject jsonObject) {
        String jsonStr = jsonObject.toString() ;
        Jsonb jsonb = JsonbBuilder.create() ;
        Animal animal = jsonb.fromJson(jsonStr,Animal.class);
        System.out.println(animal);
        return Response.ok("ok").build();
    }

    @POST
    @Path("/object/dog")
    @Produces("application/json")
    @Consumes("application/json")
    public Response getDogByObject(@Valid Dog dog) {
        System.out.println(dog);
        return Response.ok("ok").build();
    }


}
