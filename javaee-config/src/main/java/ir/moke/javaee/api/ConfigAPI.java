package ir.moke.javaee.api;


import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

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
