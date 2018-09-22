package ir.moke.javaee.config;

import ir.moke.javaee.repository.Repository;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.EnumSet;
import java.util.Set;

import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import static javax.security.enterprise.identitystore.IdentityStore.ValidationType.VALIDATE;

@ApplicationScoped
public class InMemoryIdentityStoreAuthentication implements IdentityStore {

    @EJB
    private Repository repository;

    @Override
    public int priority() {
        return 70;
    }

    @Override
    public Set<ValidationType> validationTypes() {
        return EnumSet.of(VALIDATE);
    }

    @Override
    public CredentialValidationResult validate(Credential credential) {
        UsernamePasswordCredential passwordCredential = (UsernamePasswordCredential) credential;
        String password = repository.select(passwordCredential.getCaller()).getPassword();
        if (password != null && password.equals(passwordCredential.getPasswordAsString())) {
            return new CredentialValidationResult(passwordCredential.getCaller());
        }
        return INVALID_RESULT;
    }
}
