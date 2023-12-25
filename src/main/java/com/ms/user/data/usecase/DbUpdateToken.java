package com.ms.user.data.usecase;

import com.ms.user.data.protocol.GenerateToken;
import com.ms.user.data.protocol.GetKeys;
import com.ms.user.data.protocol.UpdateTokenRepository;
import com.ms.user.domain.model.Email;
import com.ms.user.domain.model.Token;
import com.ms.user.domain.usecase.UpdateToken;

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
    public Token update(Email email) {
        Token token = this.generateToken.generateToken(
            email,
            this.getKeys.getPublicKey(),
            this.getKeys.getPrivateKey()
        );
        return this.updateTokenRepository.updateToken(email, token);
    }
}
