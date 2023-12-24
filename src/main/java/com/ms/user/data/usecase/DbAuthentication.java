package com.ms.user.data.usecase;

import com.ms.user.data.protocol.ComparePassword;
import com.ms.user.data.protocol.GenerateToken;
import com.ms.user.data.protocol.GetKeys;
import com.ms.user.data.protocol.LoadUserByEmailRepository;
import com.ms.user.data.protocol.UpdateTokenRepository;
import com.ms.user.domain.model.DefaultReturn;
import com.ms.user.domain.model.Email;
import com.ms.user.domain.model.Password;
import com.ms.user.domain.model.User;
import com.ms.user.domain.usecase.Authenticate;

public class DbAuthentication implements Authenticate {
    private final LoadUserByEmailRepository loadUserByEmailRepository;
    private final ComparePassword comparePassword;
    private final GenerateToken generateToken;
    private final GetKeys getKeys;
    private final UpdateTokenRepository updateTokenRepository;

    public DbAuthentication (
        LoadUserByEmailRepository loadUserByEmailRepository,
        ComparePassword comparePassword,
        GenerateToken generateToken,
        GetKeys getKeys,
        UpdateTokenRepository updateTokenRepository
    ) {
        this.loadUserByEmailRepository = loadUserByEmailRepository;
        this.comparePassword = comparePassword;
        this.generateToken = generateToken;
        this.getKeys = getKeys;
        this.updateTokenRepository = updateTokenRepository;
    }

    @Override
    public DefaultReturn<String> auth(Email email, Password password) {
        User user = this.loadUserByEmailRepository.loadUserByEmail(email);
        boolean isValidPassword = this.comparePassword.compare(password, user.getPassword());
        if (!isValidPassword) {
            return new DefaultReturn<String>("Invalid Password", null);
        }
        String token = this.generateToken.generateToken(
            user.getId(),
            this.getKeys.getPublicKey(),
            this.getKeys.getPrivateKey()
        );
        this.updateTokenRepository.updateToken(user.getId(), token);
        return new DefaultReturn<String>(null, token);
    }
}
