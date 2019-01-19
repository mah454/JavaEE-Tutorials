package ir.moke.javaee.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Animal {

    @NotNull(message = "Type can not be null")
    @NotEmpty (message = "Type can not be empty")
    private String type ;

    public Animal(String type) {
        this.type = type;
    }

    public Animal() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(type, animal.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
