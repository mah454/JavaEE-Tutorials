package ir.moke.javaee.api;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ir.moke.javaee.entity.Person;
import ir.moke.javaee.producer.Collection;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResources {

    @Inject
    private MongoDatabase db;

    @Inject
    private MongoClient mongoClient;

    @Inject
    @Collection("person")
    private MongoCollection<Person> collection;

    @POST
    public Response add(Person person) {
        collection.insertOne(person);
        return Response.ok(person).build();
    }
}
