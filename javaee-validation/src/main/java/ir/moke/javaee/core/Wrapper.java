package ir.moke.javaee.core;

import ir.moke.javaee.interceptor.ValidationInterceptor;
import ir.moke.javaee.validation.AnimalValidation;

import jakarta.ejb.Asynchronous;
import jakarta.ejb.Singleton;
import jakarta.enterprise.event.Observes;
import jakarta.json.JsonObject;

@Singleton
public class Wrapper {

    @Asynchronous
    @ValidationInterceptor
    public void handleAnimal(@AnimalValidation @Observes JsonObject jsonObject) {
        System.out.println("Receive : ");
        System.out.println(jsonObject.toString());
    }
}
