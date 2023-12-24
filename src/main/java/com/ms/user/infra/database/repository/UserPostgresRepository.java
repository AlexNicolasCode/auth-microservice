package com.ms.user.infra.database.repository;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.ms.user.data.protocol.GetUserByAccessTokenRepository;
import com.ms.user.data.protocol.SaveUserRepository;
import com.ms.user.data.protocol.UpdateTokenRepository;
import com.ms.user.domain.model.Email;
import com.ms.user.domain.model.Hash;
import com.ms.user.domain.model.User;
import com.ms.user.infra.database.entity.UserEntity;

@Component
public class UserPostgresRepository implements GetUserByAccessTokenRepository, SaveUserRepository, UpdateTokenRepository {
    
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
    public String updateToken(Email email, String token) {
        return this.userSpringRepository.updateToken(email.getValue(), token);
    }

    }
}
