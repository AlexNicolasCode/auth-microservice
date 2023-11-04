package com.example.profilemicroservice.data.protocol;

import com.example.profilemicroservice.domain.modal.User;

public interface GetUserByAccessTokenRepository {
    User getByAccessToken(String accessToken);
}
