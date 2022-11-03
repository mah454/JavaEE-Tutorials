package ir.moke.javaee.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;

/*@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/login.xhtml",
                errorPage = "/login-error.xhtml"
        )
)*/
@BasicAuthenticationMechanismDefinition(realmName = "userRealm")
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "java:comp/env/jdbc/h2-datasource",
        callerQuery = "SELECT PASSWORD FROM USERS WHERE USERNAME=?",
        groupsQuery = "SELECT ROLE_NAME FROM ROLES WHERE USERNAME=?"
)
@ApplicationScoped
public class SecurityConfig {
}
