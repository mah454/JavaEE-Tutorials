package ir.moke.javaee.config;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;

@BasicAuthenticationMechanismDefinition(realmName = "userRealm")
@ApplicationScoped
public class SecurityConfig {
}
