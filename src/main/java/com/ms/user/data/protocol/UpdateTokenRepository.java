package com.ms.user.data.protocol;

import com.ms.user.domain.model.Email;

public interface UpdateTokenRepository {
    String updateToken(Email email, String token);
}
