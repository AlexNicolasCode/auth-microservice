package com.ms.user.data.usecase;

import com.ms.user.data.protocol.UpdateTokenRepository;
import com.ms.user.domain.usecase.UpdateToken;

public class DbUpdateToken implements UpdateToken {
    public DbUpdateToken(UpdateTokenRepository updateTokenRepository) {
        this.updateTokenRepository = updateTokenRepository;
    }

    private UpdateTokenRepository updateTokenRepository;

    @Override
    public String update(Long userId, String token) {
        return this.updateTokenRepository.updateToken(userId, token);
    }
}
