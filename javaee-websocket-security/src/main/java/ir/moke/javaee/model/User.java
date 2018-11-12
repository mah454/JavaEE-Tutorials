package ir.moke.javaee.model;

import java.util.List;
import java.util.Objects;

public class User {
    private String username ;
    private String password ;

    private List<Group> groups ;

    public User(String username, String password,List<Group> groups) {
        this.username = username;
        this.password = password;
        this.groups = groups ;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(groups, user.groups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, groups);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", groups=" + groups +
                '}';
    }
}
