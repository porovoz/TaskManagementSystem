package com.bestapp.taskManagementSystem.dto.account;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@Component
public class JasonWebTokenProvider {
    private final SecretKey jwtActualSecret;
    private final SecretKey jwtRenewSecret;

    public JasonWebTokenProvider(@Value("${jwt.secret.actual}") String jwtActualSecret,
                                 @Value("${jwt.secret.renew}") String jwtRenewSecret) {
        this.jwtActualSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtActualSecret));
        this.jwtRenewSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtRenewSecret));
    }

    public String calculateActualToken(@NonNull User user) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant expirationTimeInstant = now.plusMinutes(60).atZone(ZoneId.systemDefault()).toInstant();
        final Date expirationTime = Date.from(expirationTimeInstant);
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setExpiration(expirationTime)
                .signWith(jwtActualSecret)
                .claim("roles", user.getRoles())
                .claim("firstName", user.getFirstName())
                .compact();
    }

    public String calculateRenewToken(@NonNull User user) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant renewExpirationTimeInstant = now.plusDays(30).atZone(ZoneId.systemDefault()).toInstant();
        final Date renewExpirationTime = Date.from(renewExpirationTimeInstant);
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setExpiration(renewExpirationTime)
                .signWith(jwtRenewSecret)
                .compact();
    }

    public boolean validateActualToken(@NonNull String actualToken) {
        return validateToken(actualToken, jwtActualSecret);
    }

    public boolean validateRenewToken(@NonNull String renewToken) {
        return validateToken(renewToken, jwtRenewSecret);
    }

    private boolean validateToken(@NonNull String token, @NonNull Key secret) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.error("Token expired", e);
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported jason web token", e);
        } catch (MalformedJwtException e) {
            log.error("Malformed jason web token");
        } catch (SignatureException e) {
            log.error("Invalid signature", e);
        } catch (Exception e) {
            log.error("Invalid token", e);
        }
        return false;
    }

    public Claims getActualClaims(@NonNull String token) {
        return getClaims(token, jwtActualSecret);
    }

    public Claims getRenewClaims(@NonNull String token) {
        return getClaims(token, jwtRenewSecret);
    }

    private Claims getClaims(@NonNull String token, Key secret) {
        return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
