package com.ms.user.infra.mock;

import com.github.javafaker.Faker;
import com.ms.user.data.protocol.Hasher;

public class HasherSpy implements Hasher {

    private int count = 0;
    private String plaintext;
    private String result = new Faker().internet().password();

    public String hash(String plaintext) {
        this.count++;
        this.plaintext = plaintext;
        return this.result;
    }

    public String getPlaintext() {
        return this.plaintext;
    }

    public String getResult() {
        return this.result;
    }

    public int getCount() {
        return this.count;
    }
}
