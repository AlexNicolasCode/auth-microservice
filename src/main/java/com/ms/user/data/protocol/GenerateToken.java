package com.ms.user.data.protocol;

import java.security.PrivateKey;
import java.security.PublicKey;

import com.ms.user.domain.model.Email;
import com.ms.user.domain.model.Token;

public interface GenerateToken {
    Token generateToken(Email email, PublicKey publicKey, PrivateKey privateKey);
}
