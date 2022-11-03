package ir.moke.javaee.security;

import ir.moke.javaee.model.User;
import ir.moke.javaee.repository.UserRepository;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import java.util.EnumSet;
import java.util.Set;

import static jakarta.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import static jakarta.security.enterprise.identitystore.IdentityStore.ValidationType.VALIDATE;

@ApplicationScoped
public class AuthenticationIdentityStore implements IdentityStore {

    @EJB
    private UserRepository repository;

    @Inject
    private Pbkdf2PasswordHash hash;

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
        UsernamePasswordCredential upc = (UsernamePasswordCredential) credential;
        String password = upc.getPasswordAsString();
        String username = upc.getCaller();
        User user = repository.find(username);
        if (hash.verify(password.toCharArray(), user.getPassword())) {
            return new CredentialValidationResult(username);
        } else {
            return INVALID_RESULT;
        }
    }
}
