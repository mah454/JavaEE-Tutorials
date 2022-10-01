package bl;

import persistence.Person;
import persistence.PersonRepo;

import javax.ejb.*;
import javax.enterprise.event.Event;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class PersonManager {

    @EJB
    public PersonRepo repo;

    @Inject
    private Event<String> event;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void addPerson(Person person) {
        repo.insert(person);
        event.fireAsync("[***] Data inserted " + person.getName() + " " + person.getFamily());
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void exceptionalAddPerson(Person person) {
        repo.exceptionallyInsert(person);
        event.fireAsync("Data inserted " + person.getName() + " " + person.getFamily());
    }

    @AfterCompletion
    public void eventConsumer(@ObservesAsync String str) {
        System.out.println("Receive: " + str);
    }

    public List<Person> personList() {
        return repo.selectAll();
    }
}
