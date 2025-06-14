package com.giveandgrow.domain.ports.input;

import com.giveandgrow.domain.model.postulation.PostulationDomain;

import java.util.List;
import java.util.UUID;

public interface PostulationServicePort {

    PostulationDomain postulate(UUID userId, UUID eventId);

    PostulationDomain accept(UUID postulationId);

    PostulationDomain reject(UUID postulationId);

    List<PostulationDomain> getPostulationsByUser(UUID userId);

    List<PostulationDomain> getPostulationsByEvent(UUID eventId);
}
