package ir.moke.javaee.security;

import javax.security.enterprise.credential.Credential;
import java.util.Set;

public class JWTCredential implements Credential {

    private final String username;
    private final Set<String> groups;


    public JWTCredential(String username, Set<String> groups) {
        this.username = username;
        this.groups = groups;
    }

    public String getUsername() {
        return username;
    }

    public Set<String> getGroups() {
        return groups;
    }
}
