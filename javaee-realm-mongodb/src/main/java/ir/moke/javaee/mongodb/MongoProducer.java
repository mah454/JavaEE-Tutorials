package ir.moke.javaee.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Inject;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@ApplicationScoped
public class MongoProducer {

    @Inject
    private MongoClient mongoClient;

    @Inject
    private BeanManager bm;

    @Produces
    @Mongo
    protected MongoCollection produceCollection(InjectionPoint ip) {
        String dbName = bm.getExtension(MongoClientExtension.class).getDatabaseName();
        Mongo mongo = getMongoAnnotation(ip);
        String collectionName = mongo.collection();
        Class<?> type = mongo.type();
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        return mongoClient.getDatabase(dbName).withCodecRegistry(pojoCodecRegistry).getCollection(collectionName, type);
    }

    private Mongo getMongoAnnotation(InjectionPoint ip) {
        return ip.getAnnotated().getAnnotation(Mongo.class);
    }
}
