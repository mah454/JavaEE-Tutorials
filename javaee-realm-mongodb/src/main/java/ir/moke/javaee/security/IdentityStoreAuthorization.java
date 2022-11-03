package ir.moke.javaee.security;

import ir.moke.javaee.model.Role;
import ir.moke.javaee.model.User;
import ir.moke.javaee.repository.UserRepository;

import jakarta.ejb.EJB;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.Function;

import static java.util.stream.Collectors.toSet;
import static jakarta.security.enterprise.identitystore.IdentityStore.ValidationType.PROVIDE_GROUPS;

public class IdentityStoreAuthorization implements IdentityStore {

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
        User user = repository.select("username", username);
        return user.getRoleList()
                .stream()
                .map(mapToString())
                .collect(toSet());
    }

    private Function<Role, String> mapToString() {
        return e -> e.getRoleType().toString();
    }
}
