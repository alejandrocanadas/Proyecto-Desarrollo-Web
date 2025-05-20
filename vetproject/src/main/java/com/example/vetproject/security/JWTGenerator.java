package com.example.vetproject.security;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

@Component
public class JWTGenerator {
    
    public static final Long EXPIRATION_TIME = 7000000L;

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String generateToken(Authentication authentication){
        //Datos necesarios para creacion

        String username = authentication.getName();
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + EXPIRATION_TIME);

        // Obtén los roles del usuario autenticado
        String roles = authentication.getAuthorities().stream()
            .map(auth -> auth.getAuthority())
            .reduce((a, b) -> a + "," + b)
            .orElse("");

        // Crear el token

        String token = Jwts.builder()
            .setSubject(username)
            .claim("roles", roles)
            .setIssuedAt(currentDate)
            .setExpiration(expirationDate)
            .signWith(key, SignatureAlgorithm.HS512)
            .compact();
        return token;
    }

    public String extractUsername(String token){
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public Key getKey() {
        return key;
    }
}
