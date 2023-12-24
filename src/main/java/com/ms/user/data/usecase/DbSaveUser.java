package com.ms.user.data.usecase;

import com.ms.user.data.protocol.Hasher;
import com.ms.user.data.protocol.SaveUserRepository;
import com.ms.user.domain.model.DefaultReturn;
import com.ms.user.domain.model.Email;
import com.ms.user.domain.model.Hash;
import com.ms.user.domain.model.Password;
import com.ms.user.domain.usecase.SaveUser;

public class DbSaveUser implements SaveUser {
    public DbSaveUser (
        Hasher<Password> hasher,
        SaveUserRepository saveUserRepository
    ) {
        this.saveUserRepository = saveUserRepository;
        this.hasher = hasher;
    }

    private SaveUserRepository saveUserRepository;
    private Hasher<Password> hasher;

    @Override
    public DefaultReturn<Long> save(String name, Email email, Password password) {
        Hash passwordHashed = this.hasher.hash(password);
        Long userId = this.saveUserRepository.save(name, email, passwordHashed);
        return new DefaultReturn<Long>(null, userId);
    }
}
