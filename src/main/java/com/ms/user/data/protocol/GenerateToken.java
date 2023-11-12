package com.ms.user.data.protocol;

import java.security.Key;

public interface GenerateToken {
    String generateToken(Long userId, Key publicKey, Key privateKey);
}
