package ir.moke.javaee.example;

import ir.moke.javaee.db.DBClient;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class StartUpInitizlizer {

    @Inject
    private DBClient client ;

    @PostConstruct
    public void init() {
        client.connect("aaa","bbb");
    }
}
