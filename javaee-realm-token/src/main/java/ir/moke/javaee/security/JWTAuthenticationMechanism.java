package ir.moke.javaee.security;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.authentication.mechanism.http.RememberMe;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.Set;

import static javax.security.enterprise.identitystore.CredentialValidationResult.Status.VALID;


/*@BasicAuthenticationMechanismDefinition
public class JWTAuthenticationMechanism {}*/

@RememberMe(
        cookieMaxAgeSeconds = 60 * 60
)
@ApplicationScoped
public class JWTAuthenticationMechanism implements HttpAuthenticationMechanism {

    private String username;
    private String password;

    @Inject
    private IdentityStoreHandler identityStoreHandler;

    @Inject
    private TokenProvider tokenProvider;

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext context) throws AuthenticationException {
        extractAuthHeader(context);

        /*if (username != null && password != null) {
            CredentialValidationResult result = identityStoreHandler.validate(new UsernamePasswordCredential(username, password));
            if (result.getStatus() == VALID) {
                return context.notifyContainerAboutLogin(result);
            } else {
                return context.responseUnauthorized();
            }
        } else if (context.isProtected()) {
            return context.responseUnauthorized();
        }

        return context.doNothing();*/

        String token = extractToken(context);
        if (username != null && password != null) {
            CredentialValidationResult result = identityStoreHandler.validate(new UsernamePasswordCredential(username, password));
            if (result.getStatus() == VALID) {
                return createToken(result, context);
            }
            return context.responseUnauthorized();
        } else if (token != null) {
            return validateToken(token, context);
        } else if (context.isProtected()) {
            return context.responseUnauthorized();
        }
        return context.doNothing();
    }

    @SuppressWarnings("unchecked")
    private AuthenticationStatus validateToken(String token, HttpMessageContext context) {
        try {
            if (tokenProvider.validateToken(token)) {
                JWTCredential credential = tokenProvider.getCredential(token);
                return context.notifyContainerAboutLogin(credential.getUsername(), (Set<String>) credential.getGroups());
            }
            return context.responseUnauthorized();
        } catch (Exception e) {
            e.printStackTrace();
            return context.responseUnauthorized();
        }
    }

    private AuthenticationStatus createToken(CredentialValidationResult result, HttpMessageContext context) {
        if (!isRememberMe(context)) {
            System.out.println("AAAAAAAA");
            String jwt = tokenProvider.createToken(result.getCallerPrincipal().getName(), result.getCallerGroups());
            context.getResponse().setHeader("Authorization", "Bearer " + jwt);
        }
        return context.notifyContainerAboutLogin(result.getCallerPrincipal(), result.getCallerGroups());
    }

    private String extractToken(HttpMessageContext context) {
        String auth = context.getRequest().getHeader("Authorization");
        if (auth != null && auth.startsWith("Bearer")) {
            return auth.substring("Bearer".length(), "Authorization".length());
        }
        return null;
    }

    public boolean isRememberMe(HttpMessageContext context) {
        return Boolean.valueOf(context.getRequest().getParameter("rememberme"));
    }

    private void extractAuthHeader(HttpMessageContext context) {
        String authHeader = context.getRequest().getHeader("Authorization");
        String b64 = authHeader.split(" ")[1];
        String decodedB64 = new String(Base64.getDecoder().decode(b64));
        if (decodedB64.split(":").length < 2) {
            username = null;
            password = null;
        } else {
            username = decodedB64.split(":")[0];
            password = decodedB64.split(":")[1];
        }
    }
}
