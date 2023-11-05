package com.ms.user.main.factory.usecase;

import org.springframework.stereotype.Service;

import com.ms.user.data.usecase.DbGetUserByAccessToken;
import com.ms.user.domain.usecase.GetUserByAccessToken;
import com.ms.user.infra.database.UserPostgresRepository;

@Service
public class MakeGetUserByAccessToken {
    
    public MakeGetUserByAccessToken (UserPostgresRepository userPostgresRepository) {
        this.userPostgresRepository = userPostgresRepository;
    }
    
    private UserPostgresRepository userPostgresRepository;
    
    public GetUserByAccessToken build () {
        return new DbGetUserByAccessToken(this.userPostgresRepository);
    }
}
