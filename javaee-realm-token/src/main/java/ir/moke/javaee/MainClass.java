package ir.moke.javaee;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.*;

public class MainClass {
    public static void main(String[] args) {
        Set<String> groups = new HashSet<>();
        groups.add("admin");
        groups.add("member");

        List<String> cities = new ArrayList<>();
        cities.add("Tehran");
        cities.add("Esfehan");
        cities.add("Shiraz");

        String jwt = Jwts.builder()
                .setSubject("Me")
                .claim("groups", groups)
                .claim("cities",cities)
                .signWith(SignatureAlgorithm.HS512,"adminpass")
                .compact();
        System.out.println(jwt);

        System.out.println("#################################################################");
        Claims claims = Jwts.parser().setSigningKey("adminpass").parseClaimsJws(jwt).getBody();
        List list = (List) claims.get("cities");

    }
}

