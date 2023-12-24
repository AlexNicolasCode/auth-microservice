package com.ms.user.data.protocol;

import com.ms.user.domain.model.User;

public interface LoadUserByIdRepository {
    User loadUserById(String userId);
}
