package com.ms.user.infra.mock;

import java.security.Key;

import com.github.javafaker.Faker;
import com.ms.user.data.protocol.GenerateToken;

public class GenerateTokenSpy implements GenerateToken {
    
    private int count = 0;
    private Long userId;
    private Key publicKey;
    private Key privateKey;
    private String result = new Faker().internet().uuid();

    public String generateToken(Long userId, Key publicKey, Key privateKey) {
        this.count++;
        this.userId = userId;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        return this.result;
    }

    public Long getUserId() {
        return this.userId;
    }

    public Key getPublicKey() {
        return this.publicKey;
    }

    public Key getPrivateKey() {
        return this.privateKey;
    }

    public String getResult() {
        return this.result;
    }

    public int getCount() {
        return this.count;
    }
}
