package com.ms.user.domain.usecase;

import com.ms.user.domain.model.User;

public interface GetUserByAccessToken {
    User getByAccessToken(String accessToken);
}