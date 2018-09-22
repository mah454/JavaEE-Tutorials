package ir.moke.javaee.security;

import ir.moke.javaee.model.User;
import ir.moke.javaee.repository.UserRepository;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import java.util.EnumSet;
import java.util.Set;

import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;

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
