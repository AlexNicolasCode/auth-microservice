package com.ms.user.infra.database.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ms.user.infra.database.entity.UserEntity;

@Repository
public interface UserSpringRepository extends CrudRepository<UserEntity, Long> {
    @Query(value = "SELECT u FROM tb_user u WHERE u.token = ?1", nativeQuery = true)
    UserEntity getByAccessToken(String accessToken);
    
    @Query(value = "UPDATE tb_user u SET token = ?2 WHERE u.id = ?1 RETURNING u.token", nativeQuery = true)
    String updateToken(Long userId, String token);
}
