package ir.moke.javaee.security;

import jakarta.security.enterprise.credential.Credential;

import java.util.List;
import java.util.Set;

public record JWTCredential(String username, Set<String> groups) implements Credential {

}
