package com.ms.user.infra.cryptography;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ms.user.data.protocol.Hasher;

public class BCryptPasswordAdapter implements Hasher {

    @Override
    public String hash(String plaintext) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(plaintext);
    }
}
