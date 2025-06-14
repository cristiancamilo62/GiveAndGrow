package com.giveandgrow.domain.ports.output;

import com.giveandgrow.domain.model.postulation.PostulationDomain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostulationRepositoryPort {

    PostulationDomain save(PostulationDomain domain);

    Optional<PostulationDomain> findById(UUID id);

    List<PostulationDomain> findByUserId(UUID userId);

    List<PostulationDomain> findByEventId(UUID eventId);

    boolean existsByUserIdAndEventId(UUID userId, UUID eventId);
}
