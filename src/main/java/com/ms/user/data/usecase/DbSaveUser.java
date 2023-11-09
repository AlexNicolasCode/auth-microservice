package com.ms.user.data.usecase;

import org.springframework.stereotype.Component;

import com.ms.user.data.protocol.Hasher;
import com.ms.user.data.protocol.SaveUserRepository;
import com.ms.user.domain.model.User;
import com.ms.user.domain.usecase.SaveUser;

@Component
public class DbSaveUser implements SaveUser {
    public DbSaveUser (
        Hasher hasher,
        SaveUserRepository saveUserRepository
    ) {
        this.saveUserRepository = saveUserRepository;
        this.hasher = hasher;
    }

    private SaveUserRepository saveUserRepository;
    private Hasher hasher;

    @Override
    public User save(User user) {
        String hash = this.hasher.hash(user.getPassword());
        user.setPassword(hash);
        return this.saveUserRepository.save(user);
    }
}
