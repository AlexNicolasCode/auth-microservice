package com.ms.user.data.usecase;

import com.ms.user.data.protocol.SaveUserRepository;
import com.ms.user.domain.model.User;
import com.ms.user.domain.usecase.SaveUser;

public class DbSaveUser implements SaveUser {
    public DbSaveUser (SaveUserRepository saveUserRepository) {
        this.saveUserRepository = saveUserRepository;
    }

    private SaveUserRepository saveUserRepository;

    @Override
    public void save(User user) {
        this.saveUserRepository.save(user);
    }
}
