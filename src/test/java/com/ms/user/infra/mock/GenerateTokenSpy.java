package com.ms.user.infra.mock;

import com.github.javafaker.Faker;
import com.ms.user.data.protocol.GenerateToken;

public class GenerateTokenSpy implements GenerateToken {
    
    private int count = 0;
    private Long userId;
    private String result = new Faker().internet().uuid();

    public String generateToken(Long userId) {
        this.count++;
        this.userId = userId;
        return this.result;
    }

    public Long getUserId() {
        return this.userId;
    }

    public String getResult() {
        return this.result;
    }

    public int getCount() {
        return this.count;
    }
}
