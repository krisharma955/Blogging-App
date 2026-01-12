package com.K955.Blog_App.Security;

import com.K955.Blog_App.Entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthUtil {

    @Value("${jwt.secret-key}")
    String jwtSecretKey;

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("userId", user.getId())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000*60*10)) //10 mins
                .signWith(getSecretKey())
                .compact();
    }

    public String generateRefreshToken(User user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("userId", user.getId())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000L *60*60*24*30*6))
                .signWith(getSecretKey())
                .compact();
    }

    public JwtUserPrincipal verifyAccessToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        Long userId = claims.get("userId", Long.class);
        String username = claims.getSubject();

        return new JwtUserPrincipal(userId, username);
    }

    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !(authentication.getPrincipal() instanceof JwtUserPrincipal userPrincipal)) {
            throw new AuthenticationCredentialsNotFoundException("No JWT found");
        }
        return userPrincipal.userId();
    }

}
