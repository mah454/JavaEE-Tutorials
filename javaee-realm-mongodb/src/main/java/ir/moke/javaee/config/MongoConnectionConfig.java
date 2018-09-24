package ir.moke.javaee.config;

import ir.moke.javaee.mongodb.MongoClientDefinition;

@MongoClientDefinition(
        name = "mongoClient",
        dbName = "dandelion",
        username = "admin",
        password = "adminpass"
)
public class MongoConnectionConfig {
}
