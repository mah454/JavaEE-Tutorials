package ir.moke.javaee.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.*;

@ApplicationScoped
public class TokenProvider {
    private Algorithm algorithm;

    @Inject
    @ConfigProperty(name = "issuer", defaultValue = "my-issuer")
    private String jwtIssuer;
    private static final String secret = "A6LF8xvFzW9yUrDVE0VxYJbF/Ik=";

    @PostConstruct
    public void init() {
        algorithm = Algorithm.HMAC512(Base64.getDecoder().decode(secret));
    }

    public DecodedJWT verify(String token) {
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(jwtIssuer)
                .build();
        return verifier.verify(token);
    }

    public String createToken(String username, Set<String> groups) {
        return JWT.create()
                .withSubject(username)
                .withClaim("groups", new ArrayList<>(groups))
                .withIssuer(jwtIssuer)
                .sign(algorithm);
    }

    public JWTCredential getCredential(String token) {
        DecodedJWT decodedJWT = verify(token);
        String username = decodedJWT.getSubject();
        Claim groups = decodedJWT.getClaim("groups");
        List<String> groupList = groups.asList(String.class);
        return new JWTCredential(username, new HashSet<>(groupList));
    }
}
