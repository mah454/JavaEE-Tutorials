package ir.moke.javaee.core;

import ir.moke.javaee.interceptor.ValidationInterceptor;
import ir.moke.javaee.validation.AnimalValidation;

import javax.ejb.Asynchronous;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.json.JsonObject;

@Singleton
public class Wrapper {

    @Asynchronous
    @ValidationInterceptor
    public void handleAnimal(@AnimalValidation @Observes JsonObject jsonObject) {
        System.out.println("Receive : ");
        System.out.println(jsonObject.toString());
    }
}
