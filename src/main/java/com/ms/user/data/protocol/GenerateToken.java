package com.ms.user.data.protocol;

import java.security.PrivateKey;
import java.security.PublicKey;

public interface GenerateToken {
    String generateToken(Long userId, PublicKey publicKey, PrivateKey privateKey);
}
