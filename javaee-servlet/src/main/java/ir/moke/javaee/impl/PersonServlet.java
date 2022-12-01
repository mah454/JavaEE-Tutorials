package ir.moke.javaee.impl;

import ir.moke.javaee.AbstractBaseServlet;
import ir.moke.javaee.model.Person;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.annotation.WebServlet;

import java.util.Map;

@WebServlet("/api/person")
public class PersonServlet extends AbstractBaseServlet<Person> {

    public PersonServlet() {
        System.out.println("PersonServlet Constructor Called");
    }

    @PostConstruct
    public void init() {
        System.out.println("PersonServlet Post Construct Called");
    }

    @Override
    public Person handleRequest(Map<String, String[]> parameterMap) {
        return new Person("John", "Wilson", 44);
    }
}
