package ir.moke.javaee.security;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.singleton;
import static jakarta.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import static jakarta.security.enterprise.identitystore.CredentialValidationResult.NOT_VALIDATED_RESULT;
import static jakarta.security.enterprise.identitystore.IdentityStore.ValidationType.VALIDATE;

@RequestScoped
public class AuthenticationIdentityStore implements IdentityStore {

    private Map<String, String> callerPassword;

    @PostConstruct
    public void init() {
        callerPassword = new HashMap<>();
        callerPassword.put("admin", "adminpass");
        callerPassword.put("mahdi", "111111");
    }

    public CredentialValidationResult validate(Credential credential) {
        CredentialValidationResult result;

        if (credential instanceof UsernamePasswordCredential usernamePassword) {
            String password = callerPassword.get(usernamePassword.getCaller());
            if (password != null && password.equals(usernamePassword.getPasswordAsString())) {
                result = new CredentialValidationResult(usernamePassword.getCaller());
            } else {
                result = INVALID_RESULT;
            }
        } else {
            result = NOT_VALIDATED_RESULT;
        }
        return result;
    }

    @Override
    public Set<ValidationType> validationTypes() {
        return singleton(VALIDATE);
    }
}
