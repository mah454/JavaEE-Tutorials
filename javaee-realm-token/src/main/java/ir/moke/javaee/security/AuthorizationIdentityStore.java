package ir.moke.javaee.security;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;
import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;
import static jakarta.security.enterprise.identitystore.IdentityStore.ValidationType.PROVIDE_GROUPS;

@RequestScoped
public class AuthorizationIdentityStore implements IdentityStore {

    private Map<String, Set<String>> groups;

    @PostConstruct
    public void init() {
        groups = new HashMap<>();
        groups.put("admin", new HashSet<>(asList("admin", "user")));
        groups.put("mahdi", new HashSet<>(Collections.singletonList("user")));
    }

    @Override
    public Set<String> getCallerGroups(CredentialValidationResult validationResult) {
        Set<String> result = groups.get(validationResult.getCallerPrincipal().getName());
        if (result == null) {
            result = emptySet();
        }
        return result;
    }

    @Override
    public Set<ValidationType> validationTypes() {
        return singleton(PROVIDE_GROUPS);
    }
}
