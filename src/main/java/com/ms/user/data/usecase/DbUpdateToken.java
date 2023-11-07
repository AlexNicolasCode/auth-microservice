package com.ms.user.data.usecase;

import com.ms.user.data.protocol.GenerateToken;
import com.ms.user.data.protocol.UpdateTokenRepository;
import com.ms.user.domain.usecase.UpdateToken;

public class DbUpdateToken implements UpdateToken {
    public DbUpdateToken(GenerateToken generateToken, UpdateTokenRepository updateTokenRepository) {
        this.generateToken = generateToken;
        this.updateTokenRepository = updateTokenRepository;
    }

    private GenerateToken generateToken;
    private UpdateTokenRepository updateTokenRepository;

    @Override
    public String update(Long userId) {
        String token = this.generateToken.generateToken(userId);
        return this.updateTokenRepository.updateToken(userId, token);
    }
}
