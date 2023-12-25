package com.ms.user.infra.database.repository;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.ms.user.data.protocol.GetUserByAccessTokenRepository;
import com.ms.user.data.protocol.GetUserCountByEmailRepository;
import com.ms.user.data.protocol.LoadUserByEmailRepository;
import com.ms.user.data.protocol.SaveUserRepository;
import com.ms.user.data.protocol.UpdateTokenRepository;
import com.ms.user.domain.model.DefaultReturn;
import com.ms.user.domain.model.Email;
import com.ms.user.domain.model.Hash;
import com.ms.user.domain.model.Password;
import com.ms.user.domain.model.Token;
import com.ms.user.domain.model.User;
import com.ms.user.infra.database.entity.UserEntity;

@Component
public class UserPostgresRepository implements GetUserByAccessTokenRepository, SaveUserRepository, UpdateTokenRepository, LoadUserByEmailRepository, GetUserCountByEmailRepository {
    
    public UserPostgresRepository(UserSpringRepository userSpringRepository) {
        this.userSpringRepository = userSpringRepository;
    }

    private UserSpringRepository userSpringRepository;

    @Override
    public Long save(String name, Email email, Hash passwordHashed) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(name);
        userEntity.setEmail(email.getValue());
        userEntity.setPassword(passwordHashed.getValue());
        UserEntity userFromDatabase = this.userSpringRepository.save(userEntity);
        return userFromDatabase.getId();
    }

    @Override
    public User getByAccessToken(String accessToken) {
        UserEntity userFromDatabase = this.userSpringRepository.getByAccessToken(accessToken);
        User userModel = new User();
        BeanUtils.copyProperties(userFromDatabase, userModel);
        return userModel;
    }

    @Override
    public Token updateToken(Email email, Token token) {
        String tokenString = this.userSpringRepository.updateToken(email.getValue(), token.getValue());
        return new Token(tokenString);
    }

    @Override
    public User loadUserByEmail(Email email) {
        UserEntity userEntity = this.userSpringRepository.getByEmail(email.getValue());
        User userModel = new User();
        try {
            Email emailFromDatabase = new Email(userEntity.getEmail());
            Password passwordFromDatabase = new Password(userEntity.getPassword());
            userModel.setName(userEntity.getName());
            userModel.setEmail(emailFromDatabase);
            userModel.setPassword(passwordFromDatabase);
            return userModel;
        } catch (Exception error) {
            return userModel;
        }
    }

    @Override
    public DefaultReturn<Integer> getUserCountByEmail(Email email) {
        try {
            int count = this.userSpringRepository.getUserCountByEmail(email.getValue());
            return new DefaultReturn<Integer>(null, count);
        } catch (Exception error) {
            return new DefaultReturn<>(error.getMessage(), null);
        }
    }
}
