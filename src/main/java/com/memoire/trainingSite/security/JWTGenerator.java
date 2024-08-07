package com.memoire.trainingSite.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTGenerator {

public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date current_date  = new Date();
        Date expiration_date = new Date(current_date.getTime() + SecurityConstants.JWT_EXPIRATION_TIME);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(current_date)
                .setExpiration(expiration_date)
                .signWith(SignatureAlgorithm.HS512 , SecurityConstants.JWT_SECRET)
                .compact();

    }

    public String getUsernameFromToken(String token){
        // deprecated to be reviewed later
        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.JWT_SECRET)
                .build()
                .parseClaimsJws(token)
                .getBody();

        System.err.println(claims.getSubject());
        return claims.getSubject();


    }

    public boolean isTokenValid(String token){
        try {
            Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET).build().parseClaimsJws(token);
            return true;
        }catch (Exception e){
            System.err.println("token is invalid");
            return false;
        }
    }

}
