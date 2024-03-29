package ir.moke.javaee.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.spi.*;

public class MongoClientExtension implements Extension {

    private String name;
    private String url;
    private int port;
    private String username;
    private String password;
    private String databaseName;

    public void initializeMongoClientDefinition(@Observes @WithAnnotations(MongoClientDefinition.class) ProcessAnnotatedType pat) {
        AnnotatedType at = pat.getAnnotatedType();
        MongoClientDefinition mongoClientDefinition = at.getAnnotation(MongoClientDefinition.class);
        this.name = mongoClientDefinition.name();
        this.url = mongoClientDefinition.url();
        this.port = mongoClientDefinition.port();
        this.databaseName = mongoClientDefinition.dbName();
        this.username = mongoClientDefinition.username();
        this.password = mongoClientDefinition.password();
    }

    public void registerMongoClientDataSource(@Observes AfterBeanDiscovery abd) {
        abd.addBean()
                .types(MongoClient.class)
                .scope(ApplicationScoped.class)
                .addQualifier(Default.Literal.INSTANCE)
                .alternative(false)
                .produceWith(o -> createConnection())
                .id(name);
    }

    private MongoClient createConnection() {
        MongoCredential credential = MongoCredential.createCredential(username, databaseName, password.toCharArray());
        MongoClientOptions mongoClientOptions = MongoClientOptions.builder().build();
        return new MongoClient(new ServerAddress(url, port), credential, mongoClientOptions);
    }

    String getDatabaseName() {
        return databaseName;
    }
}
