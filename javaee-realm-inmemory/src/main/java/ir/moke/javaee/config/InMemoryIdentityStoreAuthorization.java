package ir.moke.javaee.config;

import ir.moke.javaee.repository.Repository;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static jakarta.security.enterprise.identitystore.IdentityStore.ValidationType.PROVIDE_GROUPS;

@ApplicationScoped
public class InMemoryIdentityStoreAuthorization implements IdentityStore {

    @EJB
    private Repository repository ;

    @Override
    public int priority() {
        return 80;
    }

    @Override
    public Set<ValidationType> validationTypes() {
        return EnumSet.of(PROVIDE_GROUPS);
    }

    @Override
    public Set<String> getCallerGroups(CredentialValidationResult validationResult) {
        String username = validationResult.getCallerPrincipal().getName();
        List<String> roles = repository.select(username).getRoles();
        return new HashSet<>(roles);
    }
}
