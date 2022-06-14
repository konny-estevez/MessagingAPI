package com.rootstack.messaging.web.security;

import com.rootstack.messaging.web.model.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "hUkORJO|iNQ]Zd~p-Q\"_3['y[c_As67QnC=g}QCDD85_@L!_+z'tzLyS|IZx-s'";
    private final long TOKEN_DURATION = 36000000;
    private final String TOKEN_TYPE = "Bearer";

    public Token generateToken(UserDetails userDetails) {
        Date expiration = new Date(System.currentTimeMillis() + TOKEN_DURATION);
        String tokenValue = Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date())
                .setExpiration(expiration).signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
        return new Token(tokenValue, TOKEN_TYPE, expiration);
    }

    public Token generateToken(String username) {
        Date expiration = new Date(System.currentTimeMillis() + TOKEN_DURATION);
        String tokenValue = Jwts.builder().setSubject(username).setIssuedAt(new Date())
                .setExpiration(expiration).signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
        return new Token(tokenValue, TOKEN_TYPE, expiration);
    }

    public boolean validateToken(String token, String username) {
        return extractUsername(token).equals(username) && !isTokenExpired(token);
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
}
