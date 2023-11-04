package com.example.profilemicroservice.domain.usecase;

import com.example.profilemicroservice.domain.modal.User;

public interface GetUserByAccessToken {
    User getByAccessToken(String accessToken);
}