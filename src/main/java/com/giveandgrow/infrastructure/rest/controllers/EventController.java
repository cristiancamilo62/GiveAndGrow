package com.giveandgrow.infrastructure.rest.controllers;

import org.springframework.web.bind.annotation.*;
import com.giveandgrow.application.dto.EventDTO;
import com.giveandgrow.domain.ports.input.EventServicePort;
import com.giveandgrow.shared.exception.GiveAndGrowException;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;

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

    @PutMapping
    public ResponseEntity<String> update(@RequestBody EventDTO eventDTO) {
        try {
            System.out.println(eventDTO.toString());
            eventService.updateEvent(eventDTO);
            return ResponseEntity.ok("Event updated successfully");
        } catch (GiveAndGrowException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<EventDTO>> getEventsByOrganizationId(@PathVariable UUID id) {
        try {
            List<EventDTO> list = eventService.getEventsByOrganizationId(id);
            return ResponseEntity.ok(list);
        } catch (GiveAndGrowException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        try {
            List<EventDTO> list = eventService.getAllEvents();
            return ResponseEntity.ok(list);
        } catch (GiveAndGrowException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEventsById(@PathVariable UUID id) {
        try {
            eventService.deleteEvent(id);
            return ResponseEntity.ok("Event deleted successfully");

        } catch (GiveAndGrowException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}