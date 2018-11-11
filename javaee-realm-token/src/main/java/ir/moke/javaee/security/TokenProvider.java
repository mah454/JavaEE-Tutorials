package ir.moke.javaee.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TokenProvider {

    public String createToken(String username, Set<String> groups) {
        return Jwts.builder()
                .setSubject(username)
                .claim("groups", groups)
                .signWith(SignatureAlgorithm.HS512, "mypass")
                .compact();
    }

    @SuppressWarnings("unchecked")
    public JWTCredential getCredential(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey("mypass")
                .parseClaimsJws(token)
                .getBody();
        Set<String> groupSet = new HashSet<>((Collection<? extends String>) claims.get("groups")) ;
        return new JWTCredential(claims.getSubject(), groupSet);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey("mypass").parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
