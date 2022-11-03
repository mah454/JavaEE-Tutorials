package ir.moke.javaee.persistence.repo;

import ir.moke.javaee.persistence.enity.Client;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Singleton
@Model
public class ClientRepo {
    @PersistenceContext
    private EntityManager em;

    public void insert(Client client) {
        em.persist(client);
        em.flush();
    }

    public List<Client> selectAll() {
        return em.createQuery("select p from Person p", Client.class).getResultList();
    }
}
