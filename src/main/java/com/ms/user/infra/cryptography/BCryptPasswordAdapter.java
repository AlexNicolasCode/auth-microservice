package com.ms.user.infra.cryptography;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.ms.user.data.protocol.Hasher;

@Component
public class BCryptPasswordAdapter implements Hasher {

    @Override
    public String hash(String plaintext) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(plaintext);
    }
}
