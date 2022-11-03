package ir.moke.javaee.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;

@BasicAuthenticationMechanismDefinition(realmName = "mongodb-realm")
@ApplicationScoped
public class SecurityConfig {
}
