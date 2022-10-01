package persistence;

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
public class PersonRepo {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction tx;

    public void insert(Person person) {
        try {
            tx.begin();
            em.persist(person);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exceptionallyInsert(Person person) {
        try {
            tx.begin();
            em.persist(person);
            throw new Exception("Error on insert data");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Person> selectAll() {
        return em.createQuery("select p from Person p", Person.class)
                .getResultList();
    }
}
