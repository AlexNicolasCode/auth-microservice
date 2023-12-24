package com.ms.user.domain.usecase;

import com.ms.user.domain.model.Email;

public interface UpdateToken {
    String update(Email email);
}
