package ir.moke.javaee.config;

import javax.annotation.sql.DataSourceDefinition;
import javax.enterprise.context.ApplicationScoped;


// Read comment on DatabaseInitializer.java
/*@DataSourceDefinition(
        name = "java:comp/env/jdbc/securityDS",
        className = "oracle.jdbc.driver.OracleDriver",
        user = "admin",
        password = "adminpass",
        url = "jdbc:oracle:thin:@oracle-database:1521/pdb_javaee"
)*/
@DataSourceDefinition(
        name = "java:comp/env/jdbc/securityDS",
        className = "org.h2.jdbcx.JdbcDataSource",
        url = "jdbc:h2:~/securityTest;MODE=Oracle"
)
@ApplicationScoped
public class DataSourceConfig {
}
