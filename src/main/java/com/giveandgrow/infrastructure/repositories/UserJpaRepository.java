package com.giveandgrow.infrastructure.repositories;

import com.giveandgrow.infrastructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {

    boolean existsByIdentificationAndIdNot(String identification,UUID id);

    boolean existsByEmailAndIdNot(String email, UUID id);

    boolean existsByEmailAndPassword(String user, String password);

    Optional<UserEntity> findByEmail(String email);

}
