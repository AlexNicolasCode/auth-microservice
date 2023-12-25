package com.ms.user.infra.cryptography; 

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ms.user.data.protocol.GenerateToken;
import com.ms.user.domain.model.Email;
import com.ms.user.domain.model.Token;

@Component
public class JwtAdapter implements GenerateToken {

    @Override
    public Token generateToken(Email email, PublicKey publicKey, PrivateKey privateKey) {
        Long now = new Date().getTime();
        int sevenDays = 7 * 60 * 60 * 24 * 1000;
        Date serverDaysOnFuture = new Date(now + sevenDays);
        Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) publicKey, (RSAPrivateKey) privateKey);
        String token = JWT.create()
            .withIssuer("Bookue-api")
            .withClaim("email", email.getValue())
            .withExpiresAt(serverDaysOnFuture)
            .sign(algorithm);
        return new Token(token);
    }
}
