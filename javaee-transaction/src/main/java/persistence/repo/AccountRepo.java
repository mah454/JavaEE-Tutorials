package persistence.repo;

import persistence.enity.Account;

import javax.ejb.Singleton;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;

@Singleton
@TransactionManagement(TransactionManagementType.BEAN)
public class AccountRepo {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction tx;

    @Transactional
    public void add(Account account) {
        em.persist(account);
    }

    @Transactional
    public void updateAccount(Account account) {
        em.merge(account);
    }
}
