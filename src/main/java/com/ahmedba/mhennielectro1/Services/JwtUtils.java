package com.ahmedba.mhennielectro1.Services;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtils {

    // La clé doit être assez longue pour l'algorithme HS256 (minimum 32 caractères)
    private final String jwtSecret = "MaCleSecreteHyperLongueEtSecuriseePourMhenniElectro123!";
    private final int jwtExpirationMs = 86400000;

    // Utilisation de SecretKey (plus propre en 0.13.0)
    private SecretKey key() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateJwtToken(Authentication authentication) {
        // Cast vers notre classe personnalisée
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        String authority = userPrincipal.getAuthorities().iterator().next().getAuthority();

        return Jwts.builder()
                .subject(userPrincipal.getUsername())
                .claim("role", authority)
                .claim("nom", userPrincipal.getNom())       // ✅ Ajouté
                .claim("prenom", userPrincipal.getPrenom()) // ✅ Ajouté
                .claim("phone", userPrincipal.getPhone())   // ✅ Ajouté
                .claim("verified", userPrincipal.isVerified()) // ✅ Ajouté
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key())
                .compact();
    }

    // 2. Extraction du Username (Nouveau Parser)
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .verifyWith(key()) // Remplace setSigningKey()
                .build()
                .parseSignedClaims(token) // Remplace parseClaimsJws()
                .getPayload() // Remplace getBody()
                .getSubject();
    }

    // 3. Validation du Token
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser()
                    .verifyWith(key())
                    .build()
                    .parseSignedClaims(authToken);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // Tu peux logger l'erreur ici : "Invalid JWT token"
            return false;
        }
    }
}