package com.giveandgrow.domain.ports.input;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.giveandgrow.application.dto.EventDTO;

public interface EventServicePort {

    void createEvent(EventDTO event);

    EventDTO updateEvent(EventDTO event);

    void deleteEvent(UUID id);

    List<EventDTO> getAllEvents();

    List<EventDTO> getEventsByOrganizationId(UUID organizationId);

    List<EventDTO> getEventsByCategory(String category);

    List<EventDTO> getEventsByLocationAndStartDateTimeAndCategory(String location, LocalDateTime startDateTime, String category);

    List<EventDTO> getAllEventsByUser(UUID userId);


}
