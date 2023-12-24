package com.ms.user.data.protocol;

import java.security.PrivateKey;
import java.security.PublicKey;

import com.ms.user.domain.model.Email;

public interface GenerateToken {
    String generateToken(Email email, PublicKey publicKey, PrivateKey privateKey);
}
