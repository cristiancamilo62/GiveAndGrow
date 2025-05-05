package com.giveandgrow.infrastructure.repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.giveandgrow.infrastructure.entities.OrganizationEntity;

@Repository
public interface OrganizationJpaRepository extends JpaRepository<OrganizationEntity, UUID> {

    boolean existsByEmail(String email);

    boolean existsByEmailAndPassword(String user, String password);

    boolean existsByContactNumber(String contactNumber);

    Optional<OrganizationEntity> findByEmail(String email);



}


