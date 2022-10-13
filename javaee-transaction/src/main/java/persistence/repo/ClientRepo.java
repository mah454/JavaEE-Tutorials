package persistence.repo;

import persistence.enity.Client;

import javax.ejb.Singleton;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.util.List;

@Singleton
@TransactionManagement(TransactionManagementType.BEAN)
public class ClientRepo {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction tx;

    public void insert(Client client) {
        try {
            tx.begin();
            em.persist(client);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exceptionallyInsert(Client client) {
        try {
            tx.begin();
            em.persist(client);
            throw new Exception("Error on insert data");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Client> selectAll() {
        return em.createQuery("select p from Person p", Client.class)
                .getResultList();
    }
}
