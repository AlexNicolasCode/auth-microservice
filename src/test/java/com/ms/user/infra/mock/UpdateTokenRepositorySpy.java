package com.ms.user.infra.mock;

import com.github.javafaker.Faker;
import com.ms.user.data.protocol.UpdateTokenRepository;

public class UpdateTokenRepositorySpy implements UpdateTokenRepository {
    
    private int count = 0;
    private Long userId;
    private String token;
    private String result = new Faker().internet().uuid();

    public String updateToken(Long userId, String token) {
        this.count++;
        this.userId = userId;
        this.token = token;
        return this.result;
    }

    public Long getUserId() {
        return this.userId;
    }

    public String getToken() {
        return this.token;
    }

    public String getResult() {
        return this.result;
    }

    public int getCount() {
        return this.count;
    }
}
