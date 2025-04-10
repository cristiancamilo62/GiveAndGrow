package com.giveandgrow.infrastructure.repositories;

import com.giveandgrow.infrastructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PatientJpaRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByIdentification(String identification);

    boolean existsByEmail(String email);

    boolean existsByEmailAndPassword(String user, String password);

    Optional<UserEntity> findByEmail(String email);

}
