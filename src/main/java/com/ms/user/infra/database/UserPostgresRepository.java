package com.ms.user.infra.database;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ms.user.data.protocol.GetUserByAccessTokenRepository;
import com.ms.user.data.protocol.SaveUserRepository;
import com.ms.user.data.protocol.UpdateTokenRepository;
import com.ms.user.domain.model.User;

@Repository
public interface UserPostgresRepository extends CrudRepository<User, Long>, GetUserByAccessTokenRepository, SaveUserRepository, UpdateTokenRepository {
    @Query(value = "SELECT u FROM tb_user u WHERE u.token = ?1", nativeQuery = true)
    
    User getByAccessToken(String accessToken);
    
    @Query(value = "UPDATE tb_user u SET token = ?2 WHERE u.id = ?1 RETURNING u.token", nativeQuery = true)
    String updateToken(Long userId, String token);
}
