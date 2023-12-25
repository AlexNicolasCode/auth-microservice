package com.ms.user.infra.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ms.user.data.protocol.ComparePassword;
import com.ms.user.data.protocol.GenerateToken;
import com.ms.user.data.protocol.GetKeys;
import com.ms.user.data.protocol.Hasher;
import com.ms.user.data.protocol.LoadUserByEmailRepository;
import com.ms.user.data.protocol.SaveUserRepository;
import com.ms.user.data.protocol.SendEmail;
import com.ms.user.data.protocol.UpdateTokenRepository;
import com.ms.user.data.protocol.GetUserCountByEmailRepository;
import com.ms.user.data.usecase.DbAuthentication;
import com.ms.user.data.usecase.DbCheckEmailAlreadyUsed;
import com.ms.user.data.usecase.DbSaveUser;
import com.ms.user.data.usecase.DbUpdateToken;
import com.ms.user.data.usecase.RemoteSendWelcomeEmail;
import com.ms.user.domain.usecase.Authenticate;
import com.ms.user.domain.usecase.CheckEmailAlreadyUsed;
import com.ms.user.domain.usecase.SaveUser;
import com.ms.user.domain.usecase.SendWelcomeEmail;
import com.ms.user.domain.usecase.UpdateToken;

@Configuration
public class BeanConfiguration {

    @Bean
    SaveUser saveUser(Hasher hasher, SaveUserRepository saveUserRepository) {
        return new DbSaveUser(hasher, saveUserRepository);
    }

    @Bean
    UpdateToken updateToken(GetKeys getKeys, GenerateToken generateToken, UpdateTokenRepository updateTokenRepository) {
        return new DbUpdateToken(getKeys, generateToken, updateTokenRepository);
    }

    @Bean
    SendWelcomeEmail sendWelcomeEmail(SendEmail sendEmail) {
        return new RemoteSendWelcomeEmail(sendEmail);
    }

    @Bean
    CheckEmailAlreadyUsed checkEmailAlreadyUsed(GetUserCountByEmailRepository getUserCountByEmailRepository) {
        return new DbCheckEmailAlreadyUsed(getUserCountByEmailRepository);
    }

    @Bean
    Authenticate authenticate(
        LoadUserByEmailRepository loadUserByEmailRepository,
        ComparePassword comparePassword,
        GenerateToken generateToken,
        GetKeys getKeys,
        UpdateTokenRepository updateTokenRepository
    ) {
        return new DbAuthentication(
            loadUserByEmailRepository,
            comparePassword,
            generateToken,
            getKeys,
            updateTokenRepository
        );
    }
}