package ir.moke.javaee.entity;

import org.bson.BsonObjectId;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

public class Person {
    @BsonId
    private ObjectId id;
    private String name;
    private String family;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }
}
