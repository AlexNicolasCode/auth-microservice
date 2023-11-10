package com.ms.user.infra.mock;

import com.ms.user.data.protocol.SaveUserRepository;
import com.ms.user.domain.model.User;


public class SaveUserRepositorySpy implements SaveUserRepository {
    
    public int count = 0;
    public User user;
    public User result;

    public User save(User user) {
        this.count++;
        this.user = user;
        return user;
    }

    public User getUserParam() {
        return this.user;
    }

    public int getCount() {
        return this.count;
    }
}
