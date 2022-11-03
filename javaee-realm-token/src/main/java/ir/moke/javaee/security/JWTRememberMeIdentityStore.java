package ir.moke.javaee.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.CallerPrincipal;
import jakarta.security.enterprise.credential.RememberMeCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.RememberMeIdentityStore;
import java.util.Set;

import static jakarta.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;

@RequestScoped
public class JWTRememberMeIdentityStore implements RememberMeIdentityStore {

    @Inject
    private TokenProvider tokenProvider;

    @Override
    public CredentialValidationResult validate(RememberMeCredential rememberMeCredential) {
        try {
            DecodedJWT decodedJWT = tokenProvider.verify(rememberMeCredential.getToken());
            if (decodedJWT != null) {
                JWTCredential jwtCredential = tokenProvider.getCredential(rememberMeCredential.getToken());
                return new CredentialValidationResult(jwtCredential.username(), jwtCredential.groups());
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
