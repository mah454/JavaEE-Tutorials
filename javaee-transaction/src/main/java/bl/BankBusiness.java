package bl;

import persistence.enity.Account;
import persistence.enity.Client;
import persistence.repo.AccountRepo;
import persistence.repo.ClientRepo;

import javax.ejb.*;
import javax.enterprise.event.Event;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

@Stateless
public class BankBusiness {

    @EJB
    public ClientRepo repo;

    @EJB
    private AccountRepo accountRepo;

    @Inject
    private Event<String> event;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void openAccount(Client client) {
        Account account = new Account();
        account.setBalance(BigDecimal.ZERO);
        client.setAccount(account);
        repo.insert(client);
        event.fireAsync("[***] Data inserted " + client.getName() + " " + client.getFamily());
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void exceptionalAddClient(Client client) {
        repo.exceptionallyInsert(client);
        event.fireAsync("Data inserted " + client.getName() + " " + client.getFamily());
    }

    @AfterCompletion
    public void eventConsumer(@ObservesAsync String str) {
        System.out.println("Receive: " + str);
    }

    public List<Client> personList() {
        return repo.selectAll();
    }
}
