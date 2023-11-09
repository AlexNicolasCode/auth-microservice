package com.ms.user.infra.cryptography; 

import java.util.Date;

import org.springframework.stereotype.Component;

import com.ms.user.data.protocol.GenerateToken;

import io.jsonwebtoken.Jwts;

@Component
public class JwtAdapter implements GenerateToken {

    @Override
    public String generateToken(Long userId) {
        Long now = new Date().getTime();
        int sevenDays = 7 * 60 * 60 * 24 * 1000;
        Date serverDaysOnFuture = new Date(now + sevenDays);
        String userIdConverted = userId.toString();
        return Jwts.builder()
            .claim("id", userIdConverted)
            .expiration(serverDaysOnFuture)
            .compact();
    }
}
