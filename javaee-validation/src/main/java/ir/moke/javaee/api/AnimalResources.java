package ir.moke.javaee.api;

import ir.moke.javaee.config.ValidAnimal;
import ir.moke.javaee.model.Animal;
import ir.moke.javaee.model.Dog;

import javax.json.JsonObject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/animal")
public class AnimalResources {


    @POST
    @Path("/test")
    @Produces("application/json")
    @Consumes("application/json")
    public String testAnimal(@Valid Animal animal) {
        System.out.println(animal);
        return "ok";
    }

    @POST
    @Path("/json/dog")
    @Produces("application/json")
    @Consumes("application/json")
    public Response getDogByJson(@ValidAnimal JsonObject jsonObject) {
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
