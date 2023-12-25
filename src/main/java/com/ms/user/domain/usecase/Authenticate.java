package com.ms.user.domain.usecase;

import com.ms.user.domain.model.DefaultReturn;
import com.ms.user.domain.model.Email;
import com.ms.user.domain.model.Password;
import com.ms.user.domain.model.Token;

public interface Authenticate {
    DefaultReturn<Token> auth(Email email, Password password);
}
