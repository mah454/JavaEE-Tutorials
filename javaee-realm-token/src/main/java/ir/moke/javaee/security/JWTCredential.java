package ir.moke.javaee.security;

import javax.security.enterprise.credential.Credential;
import java.util.List;

public class JWTCredential implements Credential {

    private final String username ;
    private final List<String> groups ;


    public JWTCredential(String username, List<String> groups) {
        this.username = username;
        this.groups = groups;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getGroups() {
        return groups;
    }
}
