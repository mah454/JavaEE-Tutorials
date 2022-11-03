package ir.moke.javaee.crud;

import ir.moke.javaee.entity.Client;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Singleton
public class ClientCRUD {
    @PersistenceContext(unitName = "db-connection")
    private EntityManager em;

    public void save(Client client) {
        em.persist(client);
    }

    public void update(Client client) {
        em.merge(client);
    }

    public Client find(long id) {
        return em.find(Client.class, id);
    }

    public List<Client> find() {
        return em.createQuery("from Client", Client.class).getResultList();
    }

    public void remove(long id) {
        Client client = find(id);
        if (client != null) em.remove(client);
    }
}
