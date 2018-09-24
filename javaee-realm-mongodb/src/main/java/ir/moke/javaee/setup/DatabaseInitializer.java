package ir.moke.javaee.setup;

import ir.moke.javaee.model.Role;
import ir.moke.javaee.model.RoleType;
import ir.moke.javaee.model.User;
import ir.moke.javaee.repository.UserRepository;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

@Singleton
@Startup
public class DatabaseInitializer {

    @EJB
    private UserRepository repository;

    @Inject
    private Pbkdf2PasswordHash passwordHash ;

    @PostConstruct
    public void init() {
        if (! repository.isExist("username", "admin")) {
            User user = new User();
            user.setUsername("admin");
            user.setPassword(passwordHash.generate("adminpass".toCharArray()));
            user.addRole(new Role(RoleType.ADMIN));
            user.addRole(new Role(RoleType.MEMBER));
            repository.insert(user);
        }
    }
}
