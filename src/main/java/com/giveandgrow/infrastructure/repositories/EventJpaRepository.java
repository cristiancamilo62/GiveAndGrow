package com.giveandgrow.infrastructure.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.giveandgrow.infrastructure.entities.EventEntity;

@Repository
public interface EventJpaRepository extends JpaRepository<EventEntity, UUID> {

    List<EventEntity> findAllByOrganization_Id(UUID organizationId);

    List<EventEntity> findAllByUsers_Id(UUID userId);

    List<EventEntity> findAllByCategory(String category);

    List<EventEntity> findAllByLocationAndStartDateTimeAndCategory(String location, LocalDateTime startDateTime, String category);
    
}
