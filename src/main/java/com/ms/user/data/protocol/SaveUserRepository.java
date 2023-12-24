package com.ms.user.data.protocol;

import com.ms.user.domain.model.Email;
import com.ms.user.domain.model.Hash;

public interface SaveUserRepository {
    Long save(String name, Email email, Hash passwordHashed);
}
