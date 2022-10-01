package ir.moke.javaee.api.jaxrs;

import ir.moke.javaee.servlet.HelloService;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ws.rs.Path;

@Path("/hello")
@Singleton
public class ExampleApiImpl implements ExampleApi {

    @EJB
    private HelloService helloService;

    public ExampleApiImpl() {
        System.out.println("...  JaxRsExampleApi Object Created ...");
    }

    @Override
    public void sayHello() {
        System.out.println("[JAXRS] " + helloService.sayHello());
    }
}
