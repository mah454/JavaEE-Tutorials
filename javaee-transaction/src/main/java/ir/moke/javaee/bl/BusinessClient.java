package ir.moke.javaee.bl;

import jakarta.ejb.Local;
import ir.moke.javaee.persistence.enity.Client;

import java.util.List;

@Local
public interface BusinessClient {
    void openAccount(Client client);
    List<Client> personList();
}
