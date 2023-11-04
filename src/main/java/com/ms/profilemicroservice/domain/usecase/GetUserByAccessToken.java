package com.ms.profilemicroservice.domain.usecase;

import com.ms.profilemicroservice.domain.modal.User;

public interface GetUserByAccessToken {
    User getByAccessToken(String accessToken);
}