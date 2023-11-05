package com.ms.user.infra.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ms.user.data.protocol.GetUserByAccessTokenRepository;
import com.ms.user.domain.model.User;

@Repository
public interface UserPostgresRepository extends JpaRepository<User, Long>, GetUserByAccessTokenRepository {
    @Query(value = "SELECT u FROM tb_user u WHERE u.token = ?1", nativeQuery = true)
    User getByAccessToken(String accessToken);
}
