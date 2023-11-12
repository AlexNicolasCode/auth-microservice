package com.ms.user.data.usecase;

import com.ms.user.data.protocol.GetUserByAccessTokenRepository;
import com.ms.user.domain.model.User;
import com.ms.user.domain.usecase.GetUserByAccessToken;

public class DbGetUserByAccessToken implements GetUserByAccessToken {
    
    public DbGetUserByAccessToken (GetUserByAccessTokenRepository repository) {
        getUserByAccessTokenRepository = repository;
    }

    private GetUserByAccessTokenRepository getUserByAccessTokenRepository;

    public User getByAccessToken(String accessToken) {
        return getUserByAccessTokenRepository.getByAccessToken(accessToken);
    }
}
