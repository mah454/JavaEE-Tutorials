package ir.moke.javaee.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.List;
import java.util.Set;

public class TokenProvider {

    public String createToken(String username , Set<String> groups) {
        return Jwts.builder()
                .setSubject(username)
                .claim("groups",groups)
                .signWith(SignatureAlgorithm.HS512,"mypass")
                .compact();
    }

    @SuppressWarnings("unchecked")
    public JWTCredential getCredential(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey("mypass")
                .parseClaimsJws(token)
                .getBody();
        return new JWTCredential(claims.getSubject(), (List<String>) claims.get("groups"));
    }
}
