package com.ms.user.domain.usecase;

import com.ms.user.domain.model.DefaultReturn;
import com.ms.user.domain.model.Email;

public interface CheckEmailAlreadyUsed {
    DefaultReturn<Boolean> checkEmailAlreadyUsed(Email email);
}
