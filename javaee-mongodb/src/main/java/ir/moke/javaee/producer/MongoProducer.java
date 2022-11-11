package ir.moke.javaee.producer;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Inject;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@ApplicationScoped
public class MongoProducer {


    @Inject
    @ConfigProperty(name = "mongo.hostname", defaultValue = "localhost")
    String hostname;

    @Inject
    @ConfigProperty(name = "mongo.port", defaultValue = "27017")
    int port;

    @Inject
    @ConfigProperty(name = "mongo.dbname")
    String dbName;

    @Inject
    @ConfigProperty(name = "mongo.user")
    String user;

    @Inject
    @ConfigProperty(name = "mongo.password")
    String password;

    @Produces
    public MongoClient createMongoClient() {
        MongoCredential credential = MongoCredential.createCredential(user, dbName, password.toCharArray());
        MongoClientOptions mongoClientOptions = MongoClientOptions.builder().build();
        return new MongoClient(new ServerAddress(hostname, port), credential, mongoClientOptions);
    }

    @Produces
    public MongoDatabase createMongoDatabase(MongoClient mongoClient) {
        return mongoClient.getDatabase(dbName);
    }

    @Produces
    @Collection
    @SuppressWarnings("unchecked")
    public <T> MongoCollection<T> createCollection(InjectionPoint ip) {
        Collection annotation = getMongoAnnotation(ip);
        Type type = ((ParameterizedType) ip.getType()).getActualTypeArguments()[0];
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        return (MongoCollection<T>) createMongoClient().getDatabase(dbName).withCodecRegistry(pojoCodecRegistry).getCollection(annotation.value(), type.getClass());
    }

    private Collection getMongoAnnotation(InjectionPoint ip) {
        return ip.getAnnotated().getAnnotation(Collection.class);
    }

    public void close(@Disposes MongoClient toClose) {
        toClose.close();
    }
}
