package com.emranhss.project.jwt;

import com.emranhss.project.entity.Token;
import com.emranhss.project.entity.User;
import com.emranhss.project.repository.ITokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

@Service
public class JwtService {

    @Autowired
    private ITokenRepository tokenRepository;

    private final String SECURITY_KEY = "6SeHdW3EuWkwbT0PxoJJRt9YOH1uvppoz4C5uhmv0O3zF30YaD";


    // get all part from token
    private Claims extractAllClaims(String token) {

        return Jwts
                .parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    private SecretKey getSigningKey() {

        byte[] keyBytes = Decoders.BASE64.decode(SECURITY_KEY);
        return Keys.hmacShaKeyFor(keyBytes);

    }

    public String generateToken(User user) {
        return Jwts
                .builder()
                .setSubject(user.getEmail()) // Set Email as Subject
                .claim("role", user.getRole()) // Add user Role to Payload
                .setIssuedAt(new Date(System.currentTimeMillis())) // Set Token issue ime
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)) // Set Token Expire Time
                .signWith(getSigningKey()) // Sign the Token with Secreat key
                .compact();

    }

    public String extractUserName(String token) {

        return extractClaim(token, Claims::getSubject);

    }


    // Extract a specific Claim from the Token Claims
    public <T> T extractClaim(String token, Function<Claims, T> resolver) {

        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }


    private Date extractExpiration(String token) {

        return extractClaim(token, Claims::getExpiration);

    }

    private boolean isTokenExpired(String token) {

        return extractExpiration(token).before(new Date());

    }


    public boolean isValid(String token, UserDetails user) {

        String userName = extractUserName(token);
        boolean expired = isTokenExpired(token);

        System.out.println("Bypassing DB check. Token expired? " + expired);
        return (userName.equals(user.getUsername()) && !expired);
    }


    // get User Role From Token
    public String extractUserRole(String token) {

        return extractClaim(token, claims -> claims.get("role", String.class));
    }


}