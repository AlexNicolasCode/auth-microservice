package com.ms.user.infra.cryptography; 

import java.util.Date;

import com.ms.user.data.protocol.GenerateToken;

import io.jsonwebtoken.Jwts;

public class JwtAdapter implements GenerateToken {

    @Override
    public String generateToken(Long userId) {
        String userIdConverted = userId.toString();
        return Jwts.builder()
            .claim("id", userIdConverted)
            .expiration(new Date(System.currentTimeMillis() + 3600000))
            .compact();
    }
}
