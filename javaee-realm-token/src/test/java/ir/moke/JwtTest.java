package ir.moke;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Set;

public class JwtTest {
    private static final String secret = "A6LF8xvFzW9yUrDVE0VxYJbF/Ik=";
    private static final String issuer = "my-issuer";
    private static final Algorithm algorithm = Algorithm.HMAC512(Base64.getDecoder().decode(secret));

    public static void main(String[] args) {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlzcyI6Im15LWlzc3VlciIsImdyb3VwcyI6WyJhZG1pbiIsInVzZXIiXX0.gcDEuLbIG0dRs3hDJeJS5ZUOLFMHmLCH0ktdwa4p-saA8VTk5YFunr8LfXbCoBTthNJQfVxMsATkUdNm0JCQJg";
//        String token = createToken("admin", Set.of("admin"));
        DecodedJWT verify = verify(token);
        System.out.println(verify.getSignature());
    }

    public static DecodedJWT verify(String token) {
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(issuer)
                .build();
        return verifier.verify(token);
    }

    public static String createToken(String username, Set<String> groups) {
        return JWT.create()
                .withSubject(username)
                .withClaim("groups", new ArrayList<>(groups))
                .withIssuer(issuer)
                .sign(algorithm);
    }

}
