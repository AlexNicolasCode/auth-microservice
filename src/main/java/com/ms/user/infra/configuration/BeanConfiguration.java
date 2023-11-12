package com.ms.user.infra.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ms.user.data.protocol.GenerateToken;
import com.ms.user.data.protocol.GetKeys;
import com.ms.user.data.protocol.Hasher;
import com.ms.user.data.protocol.SaveUserRepository;
import com.ms.user.data.protocol.SendEmail;
import com.ms.user.data.protocol.UpdateTokenRepository;
import com.ms.user.data.usecase.DbSaveUser;
import com.ms.user.data.usecase.DbUpdateToken;
import com.ms.user.data.usecase.RemoteSendWelcomeEmail;
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
}