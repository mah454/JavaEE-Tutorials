package ir.moke.javaee.setup;

import ir.moke.javaee.model.Role;
import ir.moke.javaee.model.RoleType;
import ir.moke.javaee.model.User;
import ir.moke.javaee.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

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
