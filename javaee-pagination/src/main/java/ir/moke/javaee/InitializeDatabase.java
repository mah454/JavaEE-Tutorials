package ir.moke.javaee;

import ir.moke.javaee.crud.ClientCRUD;
import ir.moke.javaee.entity.Client;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

@Singleton
@Startup
public class InitializeDatabase {

    @Inject
    private ClientCRUD crud;

    @PostConstruct
    public void init() {
        Client c1 = new Client("Ali", "Mohammadi");
        Client c2 = new Client("Mahdi", "Sheikh Hosseini");
        Client c3 = new Client("Javad", "Ramezani");
        Client c4 = new Client("Hossein", "Nakhaei");

        crud.save(c1);
        crud.save(c2);
        crud.save(c3);
        crud.save(c4);
    }
}
