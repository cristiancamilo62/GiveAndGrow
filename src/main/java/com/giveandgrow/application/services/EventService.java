package com.giveandgrow.application.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.giveandgrow.application.dto.EventDTO;
import com.giveandgrow.application.mapper.EventMapperDTO;
import com.giveandgrow.domain.model.event.EventDomain;
import com.giveandgrow.domain.ports.input.EventServicePort;
import com.giveandgrow.domain.ports.output.EventRepositoryPort;
import com.giveandgrow.shared.validators.structure.GenericValidationDataStructure;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class EventService implements EventServicePort {

    private final EventRepositoryPort eventRepositoryPort;

    private final EventMapperDTO eventMapperDTO;

    private final GenericValidationDataStructure genericValidationDataStructure;
    
    
    @Override
    public void createEvent(EventDTO event) {
        
        genericValidationDataStructure.validateDataNotNullOrEmpty(event, "Event");

        EventDomain eventDomain = eventMapperDTO.toDomain(event);
        
        eventDomain.setId(UUID.randomUUID());

        eventRepositoryPort.save(eventDomain);
        
    }

    @Override
    public EventDTO updateEvent(EventDTO event) {
        
        genericValidationDataStructure.validateDataNotNullOrEmpty(event.getId(), "Id Event");

        EventDomain eventDomain = eventMapperDTO.toDomain(event);

        System.out.println(eventDomain.toString());

        return eventMapperDTO.toDTO(eventRepositoryPort.update(eventDomain, event.getUserToRegister()));

    }

    @Override
    public void deleteEvent(UUID id) {
        
        genericValidationDataStructure.validateDataNotNullOrEmpty(id, "Id Event");

        eventRepositoryPort.delete(id);
    }

    @Override
    public List<EventDTO> getAllEvents() {
       
        return eventMapperDTO.toDTOList(eventRepositoryPort.findAll());
    }

    @Override
    public List<EventDTO> getEventsByOrganizationId(UUID organizationId) {
       
        genericValidationDataStructure.validateDataNotNullOrEmpty(organizationId, "Id Organization");

        return eventMapperDTO.toDTOList(eventRepositoryPort.findByOrganizationId(organizationId));
    }

    @Override
    public List<EventDTO> getEventsByCategory(String category) {
        
        genericValidationDataStructure.validateDataNotNullOrEmpty(category, "Category");

        return eventMapperDTO.toDTOList(eventRepositoryPort.findByCategory(category));
    }

    @Override
    public List<EventDTO> getAllEventsByUser(UUID userId) {
       
        genericValidationDataStructure.validateDataNotNullOrEmpty(userId, "Id User");

        return eventMapperDTO.toDTOList(eventRepositoryPort.findAllEventsByUser(userId));
    }

    @Override
    public List<EventDTO> getEventsByLocationAndStartDateTimeAndCategory(String location, LocalDateTime startDateTime,
            String category) {
        
            return eventMapperDTO.toDTOList(eventRepositoryPort
                .findByLocationAndStartDateTimeAndCategory(location, startDateTime, category));
    }

    

    
}
