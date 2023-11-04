package com.ms.user.infra.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ms.user.data.protocol.GetUserByAccessTokenRepository;
import com.ms.user.domain.model.User;

@Repository
public interface UserPostgresRepository extends JpaRepository<User, String>, GetUserByAccessTokenRepository {
    @Override
    @Query("SELECT * FROM USER u WHERE u.token = ?accessToken")
    public User getByAccessToken(String accessToken);
}
