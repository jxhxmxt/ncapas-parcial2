package com.uca.clinic.utils;


import com.uca.clinic.domain.entities.User;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class JWTTools {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.exptime}")
    private Integer exp;


    public String generateToken(User user){
        Map<String, Objects> claims = new HashMap<>();

        return Jwts.builder()
                .claims(claims)
                .subject(user.getEmail()) // change to user.getEmail() instead of user.getUsername()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + exp))
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret)))
                .compact();
    }


    public Boolean verifyToken(String token){
        try{
            JwtParser parser = Jwts.parser().verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret)))
                    .build();
            parser.parse(token);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }



//    Replacing the getUsernameFrom and getEmailFrom method with getIdentifierFrom
    public String getIdentifierFrom(String token){
        try{
            JwtParser parser = Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret)))
                    .build();


            return parser.parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
