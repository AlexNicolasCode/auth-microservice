package com.ms.user.domain.usecase;

import com.ms.user.domain.model.DefaultReturn;
import com.ms.user.domain.model.Email;
import com.ms.user.domain.model.Password;

public interface SaveUser {
    DefaultReturn<Long> save(String name, Email email, Password password);
}
