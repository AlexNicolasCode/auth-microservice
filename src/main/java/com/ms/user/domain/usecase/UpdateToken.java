package com.ms.user.domain.usecase;

import com.ms.user.domain.model.Email;
import com.ms.user.domain.model.Token;

public interface UpdateToken {
    Token update(Email email);
}
