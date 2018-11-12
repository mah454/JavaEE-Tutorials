package ir.moke.javaee.security;

import ir.moke.javaee.model.Group;
import ir.moke.javaee.model.User;
import ir.moke.javaee.repository.UserRepository;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.EnumSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static javax.security.enterprise.identitystore.IdentityStore.ValidationType.PROVIDE_GROUPS;

@ApplicationScoped
public class AuthorizationIdentityStore implements IdentityStore {

    @EJB
    private UserRepository repository;

    @Override
    public int priority() {
        return 90;
    }

    @Override
    public Set<ValidationType> validationTypes() {
        return EnumSet.of(PROVIDE_GROUPS);
    }

    @Override
    public Set<String> getCallerGroups(CredentialValidationResult validationResult) {
        String username = validationResult.getCallerPrincipal().getName();
        User user = repository.find(username);
        return user.getGroups().stream().map(Group::getName).collect(toSet());
    }
}
