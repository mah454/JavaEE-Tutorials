package ir.moke.javaee.api;


import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/config")
public class ConfigAPI {

    @Inject
    private ConfigInventory configInventory;

    @GET
    @Path("/default-value")
    public Response getDefaultValueConfig() {
        return Response.ok().entity(configInventory.getName()).build();
    }

    /*
    * Read from microprofile-config.properties
    * */
    @GET
    @Path("/microprofile-config")
    public Response getMicroprofileConfig() {
        return Response.ok().entity(configInventory.getFamily()).build();
    }

    /*
    * Read from bootstrap.properties
    * */
    @GET
    @Path("/bootstrap")
    public Response getBootstrapConfig() {
        return Response.ok().entity(configInventory.getAge()).build();
    }

}
