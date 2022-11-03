package ir.moke.javaee.security;

import ir.moke.javaee.model.User;
import ir.moke.javaee.repository.UserRepository;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.Password;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import java.util.EnumSet;
import java.util.Set;

import static jakarta.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;

@ApplicationScoped
public class IdentityStoreAuthentication implements IdentityStore {

    @EJB
    private UserRepository repository;

    @Inject
    private Pbkdf2PasswordHash passwordHash;

    @Override
    public int priority() {
        return 70;
    }

    @Override
    public Set<ValidationType> validationTypes() {
        return EnumSet.of(ValidationType.VALIDATE);
    }

    @Override
    public CredentialValidationResult validate(Credential credential) {
        UsernamePasswordCredential usernamePasswordCredential = (UsernamePasswordCredential) credential;
        String username = usernamePasswordCredential.getCaller();
        User user = repository.select("username", username);
        String enteredPassword = usernamePasswordCredential.getPasswordAsString();
        if (passwordHash.verify(enteredPassword.toCharArray(), user.getPassword())) {
            return new CredentialValidationResult(usernamePasswordCredential.getCaller());
        } else {
            return INVALID_RESULT;
        }
    }
}
