package com.ms.user.data.usecase;

import org.springframework.stereotype.Component;

import com.ms.user.data.protocol.GenerateToken;
import com.ms.user.data.protocol.GetKeys;
import com.ms.user.data.protocol.UpdateTokenRepository;
import com.ms.user.domain.usecase.UpdateToken;

@Component
public class DbUpdateToken implements UpdateToken {
    public DbUpdateToken(
        GetKeys getKeys,
        GenerateToken generateToken,
        UpdateTokenRepository updateTokenRepository
    ) {
        this.getKeys = getKeys;
        this.generateToken = generateToken;
        this.updateTokenRepository = updateTokenRepository;
    }

    private GetKeys getKeys;
    private GenerateToken generateToken;
    private UpdateTokenRepository updateTokenRepository;

    @Override
    public String update(Long userId) {
        String token = this.generateToken.generateToken(
            userId,
            this.getKeys.getPublicKey(),
            this.getKeys.getPrivateKey()
        );
        return this.updateTokenRepository.updateToken(userId, token);
    }
}
