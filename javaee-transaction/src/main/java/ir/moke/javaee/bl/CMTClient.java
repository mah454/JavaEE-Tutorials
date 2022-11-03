package ir.moke.javaee.bl;

import ir.moke.javaee.exception.BusinessException;
import ir.moke.javaee.persistence.enity.Client;
import ir.moke.javaee.persistence.repo.ClientRepo;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;

import java.util.List;

@Stateless(name = "cmt")
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CMTClient implements BusinessClient {

    @Inject
    public ClientRepo repo;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void openAccount(Client client) {
        try {
            repo.insert(client);
        } catch (Exception e) {
            throw new BusinessException("Duplicate record found in database");
        }
    }

    public List<Client> personList() {
        return repo.selectAll();
    }
}
