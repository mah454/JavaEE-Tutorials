package ir.moke.javaee.setup;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Logger;

@Startup
@Singleton
public class DatabaseInitializer {
    private static final Logger LOGGER = Logger.getLogger(DatabaseInitializer.class.getName());

    @Resource(lookup = "java:comp/env/jdbc/securityDS")
    private DataSource dataSource;

    @Inject
    private Pbkdf2PasswordHash passwordHash;

    @PostConstruct
    public void init() {
        String password = passwordHash.generate("adminpass".toCharArray());
        // IF EXISTS statement not work on oracle database .
        executeUpdate("DROP TABLE IF EXISTS USERS");
        executeUpdate("DROP TABLE IF EXISTS ROLES");
        executeUpdate("CREATE TABLE USERS (USERNAME VARCHAR2(32),PASSWORD VARCHAR2(256))");
        executeUpdate("CREATE TABLE ROLES (USERNAME VARCHAR2(32),ROLE_NAME VARCHAR2(32))");
        executeUpdate("INSERT INTO USERS VALUES ('admin','" + password + "')");
        executeUpdate("INSERT INTO ROLES VALUES ('admin','admin')");
    }

    private void executeUpdate(String query) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                LOGGER.info("Execute Query : " + query);
                statement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
