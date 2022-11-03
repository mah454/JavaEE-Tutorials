package ir.moke.javaee.api.jaxrs;

import jakarta.ejb.Local;
import jakarta.ws.rs.GET;

@Local
public interface ExampleApi {

    @GET
    void sayHello();
}
