package com.giveandgrow.domain.ports.input;

import com.giveandgrow.application.dto.PostulationDTO;

import java.util.List;
import java.util.UUID;

public interface PostulationServicePort {

    PostulationDTO postulate(UUID userId, UUID eventId);

    PostulationDTO accept(UUID postulationId);

    PostulationDTO reject(UUID postulationId);

    List<PostulationDTO> getPostulationsByUser(UUID userId);

    List<PostulationDTO> getPostulationsByEvent(UUID eventId);
}
