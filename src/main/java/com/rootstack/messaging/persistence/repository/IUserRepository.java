package com.rootstack.messaging.persistence.repository;

import com.rootstack.messaging.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<UserEntity, UUID> {
    @Query(value = "select * from users u where u.id <> ?1 and u.active = TRUE", nativeQuery = true)
    List<UserEntity> getExceptUserListAndActive(UUID id);

    @Query(value = "select * from users u where u.email = ?1", nativeQuery = true)
    UserEntity findByEmail(String email);
}
