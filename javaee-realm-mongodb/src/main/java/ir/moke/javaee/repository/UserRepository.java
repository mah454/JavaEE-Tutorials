package ir.moke.javaee.repository;

import com.mongodb.client.MongoCollection;
import ir.moke.javaee.model.User;
import ir.moke.javaee.mongodb.Mongo;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import static com.mongodb.client.model.Filters.eq;

@Stateless
@LocalBean
public class UserRepository {

    @Inject
    @Mongo(collection = "users", type = User.class)
    private MongoCollection usersCollection;

    @SuppressWarnings("unchecked")
    public void insert(User user) {
        usersCollection.insertOne(user);
    }

    public User select(String fieldName, String value) {
        return (User) usersCollection.find(eq(fieldName, value)).first();
    }

    public boolean isExist(String fieldName, String value) {
        User user = select(fieldName, value);
        return user != null;
    }
}
