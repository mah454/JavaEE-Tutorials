package ir.moke.javaee.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationException;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import jakarta.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import jakarta.security.enterprise.authentication.mechanism.http.RememberMe;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStoreHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static jakarta.security.enterprise.identitystore.CredentialValidationResult.Status.VALID;

@RememberMe(
        cookieMaxAgeSeconds = 60 * 60,
        isRememberMeExpression = "self.isRememberMe(httpMessageContext)"
)
@ApplicationScoped
public class JWTAuthenticationMechanism implements HttpAuthenticationMechanism {

    @Inject
    private IdentityStoreHandler identityStoreHandler;

    @Inject
    private TokenProvider tokenProvider;

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext context) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String token = extractToken(context);

        if (username != null && password != null) {
            CredentialValidationResult result = identityStoreHandler.validate(new UsernamePasswordCredential(username, password));
            if (result.getStatus() == VALID) {
                return createToken(result, context);
            }
            return context.responseUnauthorized();
        } else if (token != null) {
            return verifyToken(token, context);
        } else if (context.isProtected()) {
            return context.responseUnauthorized();
        }
        return context.doNothing();
    }

    private AuthenticationStatus verifyToken(String token, HttpMessageContext context) {
        try {
            DecodedJWT decodedJWT = tokenProvider.verify(token);
            if (decodedJWT != null) {
                JWTCredential credential = tokenProvider.getCredential(token);
                return context.notifyContainerAboutLogin(credential.username(), credential.groups());
            }
            return context.responseUnauthorized();
        } catch (Exception e) {
            return context.responseUnauthorized();
        }
    }

    private AuthenticationStatus createToken(CredentialValidationResult result, HttpMessageContext context) {
        if (!isRememberMe(context)) {
            String jwt = tokenProvider.createToken(result.getCallerPrincipal().getName(), result.getCallerGroups());
            context.getResponse().setHeader("Authorization", "Bearer " + jwt);
        }
        return context.notifyContainerAboutLogin(result.getCallerPrincipal(), result.getCallerGroups());
    }

    private String extractToken(HttpMessageContext context) {
        String auth = context.getRequest().getHeader("Authorization");
        if (auth != null && auth.startsWith("Bearer")) {
            return auth.substring("Bearer".length()).trim();
        }
        return null;
    }

    public boolean isRememberMe(HttpMessageContext context) {
        return Boolean.parseBoolean(context.getRequest().getParameter("rememberme"));
    }
}
