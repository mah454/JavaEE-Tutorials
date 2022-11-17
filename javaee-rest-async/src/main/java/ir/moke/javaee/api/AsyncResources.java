package ir.moke.javaee.api;

import jakarta.ejb.Asynchronous;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.AsyncResponse;
import jakarta.ws.rs.container.Suspended;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

@Path("/process")
//@Stateless
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
public class AsyncResources {

    @GET
    @Path("async")
    @Asynchronous
    public void processor(@Suspended final AsyncResponse asyncResponse) {
        CompletableFuture.supplyAsync(this::process).thenApply(asyncResponse::resume);
    }

    @GET
    @Path("async2")
    @Asynchronous
    public CompletionStage<Response> processor2() {
        return CompletableFuture.supplyAsync(this::process);
    }

    @GET
    @Path("async3")
    @Asynchronous
    public void processor3(@Suspended final AsyncResponse asyncResponse) {
        asyncResponse.setTimeout(2, TimeUnit.SECONDS);
        asyncResponse.setTimeoutHandler(response -> response.resume(Response.noContent().build()));
        CompletableFuture.supplyAsync(this::process);
    }

    private Response process() {
        try {
            Thread.sleep(3000);
            System.out.println("Processed");
            return Response.ok("Hello").build();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
