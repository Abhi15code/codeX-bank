package com.codeX.codex_bank.config;

import java.util.Date;
import java.util.Base64.Decoder;

import org.springframework.beans.factory.annotation.Value;
import java.security.Key;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration}")
    private Long jwtExpirationDate;

    public String generateToken(Authentication authentication) {

        String userName = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        return Jwts.builder().setSubject(userName)
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(key())
                .compact();

    }

    private Key key() {
        byte[] bytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(bytes);

    }


    public String getUserName(String token){
        Claims claim = Jwts.parserBuilder()
                        .setSigningKey(key())
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                        return claim.getSubject();
    }

    public boolean validateToken(String token){
        try{

            Jwts.parserBuilder().setSigningKey(key())
            .build()
            .parse(token);
            return true;
        }catch(ExpiredJwtException  | IllegalArgumentException |MalformedJwtException  e ){
            throw new RuntimeException();


        }
    }

}
