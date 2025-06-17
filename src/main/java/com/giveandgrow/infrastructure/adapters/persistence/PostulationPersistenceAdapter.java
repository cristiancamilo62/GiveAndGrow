package com.giveandgrow.infrastructure.adapters.persistence;


import com.giveandgrow.domain.model.postulation.PostulationDomain;
import com.giveandgrow.domain.ports.output.PostulationRepositoryPort;
import com.giveandgrow.infrastructure.adapters.persistence.exceptions.IdDoesNotExistInDatabaseException;
import com.giveandgrow.infrastructure.adapters.persistence.exceptions.PostulationAlreadyExistsException;
import com.giveandgrow.infrastructure.adapters.persistence.mapper.PostulationMapperEntity;
import com.giveandgrow.infrastructure.repositories.PostulationJpaRepository;
import com.giveandgrow.shared.messages.MessageCatalog;
import com.giveandgrow.shared.messages.enums.MessageCode;
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
        postulationMapperEntity.toEntity(domain);
        return postulationMapperEntity.toDomain(postulationJpaRepository
                .save(postulationMapperEntity.toEntity(domain)));
    }

    @Override
    public Optional<PostulationDomain> findById(UUID id) {
        return postulationJpaRepository.findById(id)
                .map(postulationMapperEntity::toDomain).or
                        (() -> {
                                throw new IdDoesNotExistInDatabaseException(
                                            MessageCatalog.getContentMessage(MessageCode.M0000016),
                                            MessageCatalog.getContentMessage(MessageCode.M0000003)
                                    );
                                }
                        );
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
        if(postulationJpaRepository.existsByUserIdAndEventId(userId, eventId)){
            throw new PostulationAlreadyExistsException(
                    MessageCatalog.getContentMessage(MessageCode.M0000024),
                    MessageCatalog.getContentMessage(MessageCode.M0000023)
            );
        }
        return false;
    }
}
