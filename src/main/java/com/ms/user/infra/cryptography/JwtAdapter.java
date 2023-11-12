package com.ms.user.infra.cryptography; 

import java.security.Key;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ms.user.data.protocol.GenerateToken;

@Component
public class JwtAdapter implements GenerateToken {

    @Override
    public String generateToken(Long userId, Key publicKey, Key privateKey) {
        Long now = new Date().getTime();
        int sevenDays = 7 * 60 * 60 * 24 * 1000;
        Date serverDaysOnFuture = new Date(now + sevenDays);
        Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) publicKey, (RSAPrivateKey) privateKey);
        String token = JWT.create()
            .withIssuer("Bookue-api")
            .withClaim("id", userId)
            .withExpiresAt(serverDaysOnFuture)
            .sign(algorithm);
        return token;
    }
}
