package com.giveandgrow.domain.ports.output;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.giveandgrow.domain.model.event.EventDomain;

public interface EventRepositoryPort {

    void save(EventDomain event);

    EventDomain update(EventDomain event, UUID id);

    void delete(UUID id);

    List<EventDomain> findAll();

    Optional<EventDomain> findById(UUID id);

    List<EventDomain> findByOrganizationId(UUID organizationId);

    List<EventDomain> findAllEventsByUser(UUID userId);

    List<EventDomain> findByCategory(String category);

    List<EventDomain> findByLocationAndStartDateTimeAndCategory(String location, LocalDateTime startDateTime, String category);

    boolean existsById(UUID id);

}
