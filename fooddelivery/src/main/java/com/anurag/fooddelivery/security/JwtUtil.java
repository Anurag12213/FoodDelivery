package com.anurag.fooddelivery.security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Jwts;

import org.springframework.stereotype.Component;

import  java.util.Date;

@Component
public class JwtUtil {
    //secretKey
    private final String SECRET =
            "mysecretkeymysecretkeymysecretkey123456789123";

    //GenerateToken
   public String generateToken(String email){
       return Jwts.builder()
               .setSubject(email)
               .setIssuedAt(new Date())
               .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
               .signWith(SignatureAlgorithm.HS256,SECRET)
               .compact();
   }

   //ExtractEmail
    public String extractEmail(String token){
       return getClaims(token)
               .getSubject();
    }

    //validateToken
    public boolean validateToken(String token,String email){

       String extractedEmail=extractEmail(token);
       return   extractedEmail.equals(email) && !isTokenExpired(token);
    }


    //checkExpiry
    private boolean isTokenExpired(String token){
       return getClaims(token)
               .getExpiration()
               .before(new Date());
    }

    //getClaims
    private Claims getClaims(String token){
       return
               Jwts.parser()
                       .setSigningKey(SECRET)
                       .parseClaimsJws(token)
                       .getBody();
    }
}
