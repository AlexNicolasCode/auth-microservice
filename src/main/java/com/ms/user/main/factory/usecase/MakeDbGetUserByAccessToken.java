package com.ms.user.main.factory.usecase;

import org.springframework.beans.factory.annotation.Autowired;

import com.ms.user.data.usecase.DbGetUserByAccessToken;
import com.ms.user.domain.usecase.GetUserByAccessToken;
import com.ms.user.infra.database.UserPostgresRepository;

public class MakeDbGetUserByAccessToken {
    @Autowired
	UserPostgresRepository userRepository;

    public GetUserByAccessToken build () {
        return new DbGetUserByAccessToken(userRepository);
    }
}
