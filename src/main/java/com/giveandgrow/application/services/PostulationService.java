package com.giveandgrow.application.services;

import com.giveandgrow.application.dto.PostulationDTO;
import com.giveandgrow.application.mapper.PostulationMapperDTO;
import com.giveandgrow.domain.model.event.EventDomain;
import com.giveandgrow.domain.model.postulation.PostulationDomain;
import com.giveandgrow.domain.model.user.UserDomain;
import com.giveandgrow.domain.ports.input.PostulationServicePort;
import com.giveandgrow.domain.ports.output.EventRepositoryPort;
import com.giveandgrow.domain.ports.output.PostulationRepositoryPort;
import com.giveandgrow.domain.ports.output.UserRepositoryPort;
import com.giveandgrow.infrastructure.adapters.persistence.exceptions.PostulationAlreadyExistsException;
import com.giveandgrow.shared.validators.structure.GenericValidationDataStructure;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PostulationService implements PostulationServicePort {

    private final PostulationMapperDTO postulationMapperDTO;

    private final PostulationRepositoryPort postulationRepository;

    private final EventRepositoryPort eventRepository;

    private final UserRepositoryPort userRepository;

    private final GenericValidationDataStructure genericValidationDataStructure;


    @Override
    public PostulationDTO postulate(UUID userId, UUID eventId) {

       genericValidationDataStructure.validateDataNotNullOrEmpty(userId, "user Id");

       genericValidationDataStructure.validateDataNotNullOrEmpty(eventId, "event Id");

       postulationRepository.existsByUserIdAndEventId(userId, eventId);

        PostulationDomain postulation = PostulationDomain.builder()
                .id(UUID.randomUUID())
                .userId(userId)
                .eventId(eventId)
                .status(PostulationDomain.PostulationStatus.PENDING)
                .createAt(String.valueOf(LocalDateTime.now()))
                .build();

        return postulationMapperDTO.toDTO(postulationRepository.save(postulation));
    }

    @Override
    public PostulationDTO accept(UUID postulationId) {
        genericValidationDataStructure.validateDataNotNullOrEmpty(postulationId, "postulation Id");

        // 1. Obtener y validar postulación
        Optional<PostulationDomain> postulationDomain = postulationRepository
                .findById(postulationId);

        // 2. Cambiar estado a ACEPTADO
        postulationDomain.get().setStatus(PostulationDomain.PostulationStatus.ACCEPTED);
        postulationRepository.save(postulationDomain.get());

        // 3. Obtener evento y usuario
        UUID eventId = postulationDomain.get().getEventId();
        UUID userId = postulationDomain.get().getUserId();



        EventDomain event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        UserDomain user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // 4. Agregar usuario al evento
        event.addUser(user);

        // 5. Guardar evento → aquí se llena la tabla event_user
        eventRepository.save(event);

        // 6. Retornar resultado
        return postulationMapperDTO.toDTO(postulationDomain.get());
    }


    @Override
    public PostulationDTO reject(UUID postulationId) {
        genericValidationDataStructure.validateDataNotNullOrEmpty(postulationId, "postulation Id");
        PostulationDomain postulationDomain = postulationRepository
                .findById(postulationId).orElseThrow(()-> new IllegalArgumentException("Postulation does not exist"));
        postulationDomain.setStatus(PostulationDomain.PostulationStatus.REJECTED);
        return postulationMapperDTO.toDTO(postulationRepository.save(postulationDomain));
    }

    @Override
    public List<PostulationDTO> getPostulationsByUser(UUID userId) {
        return postulationMapperDTO.toDTOList(postulationRepository.findByUserId(userId));
    }

    @Override
    public List<PostulationDTO> getPostulationsByEvent(UUID eventId) {
        return postulationMapperDTO.toDTOList(postulationRepository.findByEventId(eventId));
    }
}
