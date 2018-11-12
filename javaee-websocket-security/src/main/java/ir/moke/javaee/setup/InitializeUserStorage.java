package ir.moke.javaee.setup;

import ir.moke.javaee.model.Group;
import ir.moke.javaee.model.User;
import ir.moke.javaee.repository.UserRepository;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Startup
public class InitializeUserStorage {

    @EJB
    private UserRepository repository;

    @Inject
    private Pbkdf2PasswordHash hash;

    @PostConstruct
    public void init() {
        Group chat = new Group("chat");
        Group restApi = new Group("restApi");

        List<Group> minAccess = new ArrayList<>();
        minAccess.add(restApi);

        List<Group> fullAccess = new ArrayList<>();
        fullAccess.add(restApi);
        fullAccess.add(chat);

        User adminUser = new User("admin", hash.generate("adminpass".toCharArray()), fullAccess);
        User mahdiUser = new User("mahdi", hash.generate("111111".toCharArray()), minAccess);

        repository.add(adminUser);
        repository.add(mahdiUser);
    }
}
