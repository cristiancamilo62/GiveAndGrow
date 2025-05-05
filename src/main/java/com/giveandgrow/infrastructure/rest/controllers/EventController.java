package com.giveandgrow.infrastructure.rest.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.giveandgrow.application.dto.EventDTO;
import com.giveandgrow.domain.ports.input.EventServicePort;
import com.giveandgrow.shared.exception.GiveAndGrowException;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/events")
public class EventController {
    
    private final EventServicePort eventService;

    @GetMapping("/dummy")
    public EventDTO dummy() {
        return new EventDTO();
    }
    
    @PostMapping()
    public ResponseEntity<String> create(@RequestBody EventDTO  eventDTO) {
        
        try {
            eventService.createEvent(eventDTO);
            return ResponseEntity.ok("Event created successfully");
        } catch (GiveAndGrowException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        
    }

    @PutMapping()
    public ResponseEntity<String> update(@RequestBody EventDTO eventDTO) {
        try {
            eventService.updateEvent(eventDTO);
            return ResponseEntity.ok("Event updated successfully");
        } catch (GiveAndGrowException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<EventDTO>> getEventsByOrganizationId(@PathVariable UUID id) {
        try {
            List<EventDTO> list = eventService.getAllEventsByUser(id);
            return ResponseEntity.ok(list);
        } catch (GiveAndGrowException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    
    
    
}