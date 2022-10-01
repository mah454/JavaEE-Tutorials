package ir.moke.javaee.api.custom;


import ir.moke.javaee.annotation.Command;
import ir.moke.javaee.annotation.CustomAPI;
import ir.moke.javaee.servlet.HelloService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

@CustomAPI
public class CustomServiceTwo {

    @EJB
    private HelloService helloService;

    public CustomServiceTwo() {
        System.out.println("...... CustomServiceTwo Object Created ......");
    }

    @PostConstruct
    public void init() {
        System.out.println(" >>> Initialized <<<");
    }

    @Command("bye")
    public void sayHello() {
        System.out.println(helloService.sayBye());
    }
}
