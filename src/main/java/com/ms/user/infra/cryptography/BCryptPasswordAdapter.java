package com.ms.user.infra.cryptography;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.ms.user.data.protocol.ComparePassword;
import com.ms.user.data.protocol.Hasher;
import com.ms.user.domain.model.Hash;
import com.ms.user.domain.model.Password;

@Component
public class BCryptPasswordAdapter implements Hasher, ComparePassword {

    @Override
    public Hash hash(String plaintext) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashString = encoder.encode(plaintext);
        return new Hash(hashString);
    }

    @Override
    public boolean compare(Password password, Password passwordEncoded) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean isValidPassword = encoder.matches(password.getValue(), passwordEncoded.getValue());
        return isValidPassword;
    }
}
