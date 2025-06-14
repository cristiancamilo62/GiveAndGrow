package com.giveandgrow.infrastructure.repositories;


import com.giveandgrow.infrastructure.entities.PostulationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostulationJpaRepository extends JpaRepository<PostulationEntity, UUID> {

    List<PostulationEntity> findByUserId(UUID postulationId);

    List<PostulationEntity> findByEventId(UUID eventId);

    boolean existsByUserIdAndEventId(UUID userId, UUID eventId);
}
