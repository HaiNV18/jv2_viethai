package com.micro.account_service.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtUtil {

    private static final Key SECRET_KEY = Keys.hmacShaKeyFor("MySuperSecretKeyForJWTAuthentication2024!!!".getBytes());
    private static final long EXPIRATION_TIME = 4 * 60 * 60 * 1000; // 4h

    public static String generateToken(Map<String, Object> claims) {//claims chứa vài thông tin cơ bản muốn chia sẻ
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    // decode
    public static Claims validateToken(String token) throws JwtException {
        return Jwts.parserBuilder() //trả về thông tin kèm theo bên trong token
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static Date getExpiration(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }
}
