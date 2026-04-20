// A class which is used to generate and validate token 
package com.example.Backend.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

import com.example.Backend.Services.UserServices;

public class JwtUtil {

    private static final String SECRET = "mysecretkeymysecretkeymysecretkey"; // min 32 chars
    private static final Key KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    // Generates a token 
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // Username 
                .setIssuedAt(new Date()) // creates current date and time according to the system
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60*60)) // Expire after 1 hour
                .signWith(KEY) // stamp
                .compact();
    }
    // Validate the token - checks whether the token is expired or not
    public static Claims validateToken(String token) {
    return Jwts.parserBuilder()
            .setSigningKey(KEY)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
    // First remove the "Bearer " part from JWT and then we can use getSubject() on the remaining string
    public static String extractUsername(String token) {
    return validateToken(token).getSubject();
}
}