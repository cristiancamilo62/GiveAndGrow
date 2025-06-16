package com.giveandgrow.infrastructure.adapters.persistence;


import com.giveandgrow.domain.model.postulation.PostulationDomain;
import com.giveandgrow.domain.ports.output.PostulationRepositoryPort;
import com.giveandgrow.infrastructure.adapters.persistence.mapper.PostulationMapperEntity;
import com.giveandgrow.infrastructure.entities.PostulationEntity;
import com.giveandgrow.infrastructure.repositories.PostulationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostulationPersistenceAdapter implements PostulationRepositoryPort {

    private final PostulationJpaRepository postulationJpaRepository;
    private final PostulationMapperEntity postulationMapperEntity;


    @Override
    public PostulationDomain save(PostulationDomain domain) {

        PostulationEntity p = postulationMapperEntity.toEntity(domain);

        System.out.println("hola"+p.toString());

        return postulationMapperEntity.toDomain(postulationJpaRepository
                .save(postulationMapperEntity.toEntity(domain)));
    }

    @Override
    public Optional<PostulationDomain> findById(UUID id) {
        return postulationJpaRepository.findById(id)
                .map(postulationMapperEntity::toDomain);
    }

    @Override
    public List<PostulationDomain> findByUserId(UUID userId) {
        return postulationJpaRepository.findByUserId(userId)
                .stream()
                .map(postulationMapperEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostulationDomain> findByEventId(UUID eventId) {
        return postulationJpaRepository.findByEventId(eventId)
                .stream()
                .map(postulationMapperEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByUserIdAndEventId(UUID userId, UUID eventId) {
        return postulationJpaRepository.existsByUserIdAndEventId(userId, eventId);
    }
}
