package ir.moke;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class TestJWT {
    public static void main(String[] args) {
        String compact = Jwts.builder()
                .setSubject("AAA")
                .claim("Key", "Mahdi")
                .setAudience("WOWWW")
                .setHeaderParam("Header Param", "Simple header")
                .setId("123")
                .setIssuedAt(new Date())
                .setIssuer("Administrator")
                .signWith(SignatureAlgorithm.HS512,"SAMPLEEEE")
                .compact();
        System.out.println(compact);
    }
}
