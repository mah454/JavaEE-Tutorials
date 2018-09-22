package ir.moke.javaee.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private List<Role> roleList;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.roleList = new ArrayList<>();
    }

    public User() {
        this.roleList = new ArrayList<>();
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

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public void addRole(Role role) {
        this.roleList.add(role);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roleList=" + roleList +
                '}';
    }
}
