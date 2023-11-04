package com.ms.profilemicroservice.data.protocol;

import com.ms.profilemicroservice.domain.modal.User;

public interface GetUserByAccessTokenRepository {
    User getByAccessToken(String accessToken);
}
