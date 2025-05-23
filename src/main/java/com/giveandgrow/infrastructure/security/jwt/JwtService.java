package com.giveandgrow.infrastructure.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Component
public class JwtService {

    // Idealmente deberías usar una clave secreta Base64 de al menos 256 bits
    private static final String SECRET = "g5&8vR!qZ2#jK1@xP9*wD4zY$L0eM6sT";

    /**
     * Construye la clave de firma HMAC-SHA256 a partir de tu secreto.
     */
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Genera un token JWT con el rol incluido en las reclamaciones.
     */
    public String generateToken(UserDetails userDetails, String role, UUID id) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        claims.put("id", id);
        return createToken(claims, userDetails.getUsername());
    }

    // Y el método createToken se queda así:
    private String createToken(Map<String, Object> claims, String subject) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + 1000L * 60 * 60 * 10))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    /** Extrae el nombre de usuario (subject) del token */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /** Extrae la fecha de expiración del token */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /** Extrae una reclamación genérica usando un resolver */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /** Parsea todas las reclamaciones usando la clave de firma */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /** Comprueba si el token ha expirado */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Valida que el token pertenezca al usuario y no esté expirado.
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /** Extrae el rol custom de las reclamaciones */
    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }
}
