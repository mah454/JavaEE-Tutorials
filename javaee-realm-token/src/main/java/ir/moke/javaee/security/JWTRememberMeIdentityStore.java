package ir.moke.javaee.security;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.security.enterprise.CallerPrincipal;
import javax.security.enterprise.credential.RememberMeCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.RememberMeIdentityStore;
import java.util.Set;

import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;

@RequestScoped
public class JWTRememberMeIdentityStore implements RememberMeIdentityStore {

    @Inject
    private TokenProvider tokenProvider;

    @SuppressWarnings("unchecked")
    @Override
    public CredentialValidationResult validate(RememberMeCredential rememberMeCredential) {
        try {
            if (tokenProvider.validateToken(rememberMeCredential.getToken())) {
                JWTCredential jwtCredential = tokenProvider.getCredential(rememberMeCredential.getToken());
                return new CredentialValidationResult(jwtCredential.getUsername(), (Set<String>) jwtCredential.getGroups());
            }
            return INVALID_RESULT;
        } catch (Exception e) {
            return INVALID_RESULT;
        }
    }

    @Override
    public String generateLoginToken(CallerPrincipal callerPrincipal, Set<String> groups) {
        return tokenProvider.createToken(callerPrincipal.getName(),groups);
    }

    @Override
    public void removeLoginToken(String token) {

    }
}
