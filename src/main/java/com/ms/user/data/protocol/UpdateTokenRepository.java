package com.ms.user.data.protocol;

public interface UpdateTokenRepository {
    String updateToken(Long userId, String token);
}
