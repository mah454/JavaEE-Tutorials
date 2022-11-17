package ir.moke.javaee.api;

import ir.moke.javaee.Processor;
import jakarta.annotation.Resource;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.enterprise.concurrent.ManagedExecutorService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Path("/process")
@Stateless
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
public class ClientResources {

    @EJB
    private Processor processor;

    @Resource
    private ManagedExecutorService mes ;

    @GET
    @Path("async")
    public Future<Response> logProcessing() {
        CompletableFuture<String> future = mes.supplyAsync(this::process);
        return future.thenApply(item -> Response.ok(item).build());
    }

    private String process() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Hello";
    }
}
