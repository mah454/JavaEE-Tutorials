package ir.moke.javaee.example;

import ir.moke.javaee.cdi.extension.DBConfiguration;

@DBConfiguration(
        name = "Database",
        description = "Simulate Database Connection",
        username = "admin",
        password = "adminpass",
        hostname = "my-server-ip-address"
)
public class DatabaseConfig {
}
