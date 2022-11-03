package ir.moke.javaee.api.custom;


import ir.moke.javaee.annotation.Command;
import ir.moke.javaee.annotation.CustomAPI;
import ir.moke.javaee.servlet.HelloService;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.io.Serializable;

@CustomAPI
@Stateless
public class CustomServiceOne implements Serializable {

    @EJB
    private HelloService helloService;

    public CustomServiceOne() {
        System.out.println("...... CustomServiceOne Object Created ......");
    }

    @PostConstruct
    public void init() {
        System.out.println(" >>> Initialized <<<");
    }

    @Command("hello")
    public void sayHello() {
        System.out.println(helloService.sayHello());
    }
}
