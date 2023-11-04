package com.ms.user.domain.usecase;

import com.ms.user.domain.modal.User;

public interface GetUserByAccessToken {
    User getByAccessToken(String accessToken);
}