package ir.moke.javaee.model;

import java.util.Objects;

public class Information {
    private String name ;
    private String family ;

    public Information(String name, String family) {
        this.name = name;
        this.family = family;
    }

    public Information() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Information that = (Information) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(family, that.family);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, family);
    }

    @Override
    public String toString() {
        return "Information{" +
                "name='" + name + '\'' +
                ", family='" + family + '\'' +
                '}';
    }
}
