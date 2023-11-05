package com.ms.user.data.protocol;

import com.ms.user.domain.model.User;

public interface GetUserByAccessTokenRepository {
    User getByAccessToken(String accessToken);
}
