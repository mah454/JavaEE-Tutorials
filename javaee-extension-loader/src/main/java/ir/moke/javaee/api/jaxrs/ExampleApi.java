package ir.moke.javaee.api.jaxrs;

import javax.ejb.Local;
import javax.ws.rs.GET;

@Local
public interface ExampleApi {

    @GET
    void sayHello();
}
