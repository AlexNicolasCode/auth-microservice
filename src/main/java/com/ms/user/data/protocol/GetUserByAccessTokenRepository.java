package com.ms.user.data.protocol;

import com.ms.user.domain.modal.User;

public interface GetUserByAccessTokenRepository {
    User getByAccessToken(String accessToken);
}
