package com.giveandgrow.infrastructure.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giveandgrow.infrastructure.entities.EventEntity;

public interface EventJpaRepository extends JpaRepository<EventEntity, UUID> {

    List<EventEntity> findAllByOrganization_Id(UUID organizationId);

    List<EventEntity> findAllByUsers_Id(UUID userId);
    
}
