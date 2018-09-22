package ir.moke.javaee.config;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;

@BasicAuthenticationMechanismDefinition(realmName = "mongodb-realm")
@ApplicationScoped
public class SecurityConfig {
}
