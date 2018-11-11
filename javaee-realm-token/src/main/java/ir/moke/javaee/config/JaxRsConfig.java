package ir.moke.javaee.config;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@DeclareRoles({"admin","user"})
@ApplicationPath("/api/v1")
public class JaxRsConfig extends Application {
}
