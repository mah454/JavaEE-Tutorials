package ir.moke.javaee.config;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;

/*@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/login.xhtml",
                errorPage = "/login-error.xhtml"
        )
)*/
//@BasicAuthenticationMechanismDefinition(realmName = "userRealm")
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "java:comp/env/jdbc/securityDS",
        callerQuery = "SELECT PASSWORD FROM USERS WHERE USERNAME=?",
        groupsQuery = "SELECT ROLE_NAME FROM ROLES WHERE USERNAME=?"
)
@ApplicationScoped
public class SecurityConfig {
}
