package com.ms.user.data.protocol;

import com.ms.user.domain.model.Email;
import com.ms.user.domain.model.User;

public interface LoadUserByEmailRepository {
    User loadUserByEmail(Email email);
}
