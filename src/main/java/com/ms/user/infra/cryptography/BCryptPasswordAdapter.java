package com.ms.user.infra.cryptography;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.ms.user.data.protocol.Hasher;
import com.ms.user.domain.model.Hash;

@Component
public class BCryptPasswordAdapter implements Hasher {

    @Override
    public Hash hash(String plaintext) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashString = encoder.encode(plaintext);
        return new Hash(hashString);
    }

}
