package com.ms.user.infra.cryptography;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.ms.user.data.protocol.Hasher;
import com.ms.user.domain.model.Hash;

@Component
public class BCryptPasswordAdapter<T> implements Hasher<T> {

    @Override
    public Hash hash(T plaintext) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashString = encoder.encode(plaintext.toString());
        return new Hash(hashString);
    }

}
