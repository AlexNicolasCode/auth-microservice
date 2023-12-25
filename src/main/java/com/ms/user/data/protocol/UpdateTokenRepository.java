package com.ms.user.data.protocol;

import com.ms.user.domain.model.Email;
import com.ms.user.domain.model.Token;

public interface UpdateTokenRepository {
    Token updateToken(Email email, Token token);
}
