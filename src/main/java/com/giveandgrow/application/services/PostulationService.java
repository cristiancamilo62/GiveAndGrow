package com.giveandgrow.application.services;

import com.giveandgrow.application.mapper.PostulationMapperDTO;
import com.giveandgrow.domain.model.postulation.PostulationDomain;
import com.giveandgrow.domain.ports.input.PostulationServicePort;
import com.giveandgrow.domain.ports.output.PostulationRepositoryPort;
import com.giveandgrow.shared.validators.structure.GenericValidationDataStructure;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PostulationService implements PostulationServicePort {

    private final PostulationMapperDTO postulationMapperDTO;

    private final PostulationRepositoryPort postulationRepository;

    private final GenericValidationDataStructure genericValidationDataStructure;


    @Override
    public PostulationDomain postulate(UUID userId, UUID eventId) {
       genericValidationDataStructure.validateDataNotNullOrEmpty(userId, "user Id");
       genericValidationDataStructure.validateDataNotNullOrEmpty(eventId, "event Id");

       if(postulationRepository.existsByUserIdAndEventId(userId, eventId)) {
           throw new IllegalStateException("Postulation already exists");
       }

        PostulationDomain postulation = PostulationDomain.builder()
                .id(UUID.randomUUID())
                .userId(userId)
                .eventId(eventId)
                .status(PostulationDomain.PostulationStatus.PENDING)
                .createdAt(String.valueOf(LocalDateTime.now()))
                .build();

        return postulationRepository.save(postulation);
    }

    @Override
    public PostulationDomain accept(UUID postulationId) {
        genericValidationDataStructure.validateDataNotNullOrEmpty(postulationId, "postulation Id");
        PostulationDomain postulationDomain = postulationRepository
                .findById(postulationId).orElseThrow(()-> new IllegalArgumentException("Postulation does not exist"));
        postulationDomain.setStatus(PostulationDomain.PostulationStatus.ACCEPTED);
        return postulationRepository.save(postulationDomain);
    }

    @Override
    public PostulationDomain reject(UUID postulationId) {
        genericValidationDataStructure.validateDataNotNullOrEmpty(postulationId, "postulation Id");
        PostulationDomain postulationDomain = postulationRepository
                .findById(postulationId).orElseThrow(()-> new IllegalArgumentException("Postulation does not exist"));
        postulationDomain.setStatus(PostulationDomain.PostulationStatus.REJECTED);
        return postulationRepository.save(postulationDomain);
    }

    @Override
    public List<PostulationDomain> getPostulationsByUser(UUID userId) {
        return postulationRepository.findByUserId(userId);
    }

    @Override
    public List<PostulationDomain> getPostulationsByEvent(UUID eventId) {
        return postulationRepository.findByEventId(eventId);
    }
}
