package ir.moke.javaee.api;

import jakarta.ejb.Singleton;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/oauth")
@Singleton
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class OAuthResource {

    @GET
    @Path("callback")
    public Response callback(@Context HttpServletRequest request) {

        System.out.println("callback ...");
        return Response.ok().build();
    }
}
