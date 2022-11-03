package ir.moke.javaee.servlet;

import jakarta.ejb.Stateless;

@Stateless
public class HelloService {

    public HelloService() {
        System.out.println("## HelloService Object Created ##");
    }

    public String sayHello() {
        return "Hello ..." ;
    }

    public String sayBye() {
        return "Bye bye !";
    }
}
