package ir.moke.javaee.bl;

import ir.moke.javaee.exception.BusinessException;
import ir.moke.javaee.persistence.enity.Client;
import ir.moke.javaee.persistence.repo.ClientRepo;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.inject.Inject;
import jakarta.transaction.UserTransaction;

import java.util.List;

@Stateless(name = "bmt")
@TransactionManagement(TransactionManagementType.BEAN)
public class BMTClient implements BusinessClient {

    @Inject
    public ClientRepo repo;

    @Inject
    private UserTransaction utx;

    public void openAccount(Client client) {
        try {
            utx.begin();
            repo.insert(client);
            utx.commit();
        } catch (Exception e) {
            throw new BusinessException("Duplicate record found in database");
        }
    }

    public List<Client> personList() {
        return repo.selectAll();
    }
}
