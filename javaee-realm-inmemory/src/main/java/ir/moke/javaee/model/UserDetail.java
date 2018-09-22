package ir.moke.javaee.model;

import java.util.ArrayList;
import java.util.List;

public class UserDetail {

    private String uid;
    private String username;
    private String password;

    private List<String> roles;

    public UserDetail(String uid, String username, String password) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.roles = new ArrayList<>();
    }

    public UserDetail() {
        this.roles = new ArrayList<>();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public void addRole(String roleName) {
        this.getRoles().add(roleName);
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
