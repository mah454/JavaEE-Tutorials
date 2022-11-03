package ir.moke.javaee.example;

import ir.moke.javaee.db.DBClient;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

@Singleton
@Startup
public class StartUpInitializer {

    @Inject
    private DBClient client;

    @PostConstruct
    public void init() {
        System.out.println("### Initialize ###");
        client.connect();
    }
}
