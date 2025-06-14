package com.giveandgrow.infrastructure.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.giveandgrow.application.dto.EventDTO;
import com.giveandgrow.domain.ports.input.EventServicePort;
import com.giveandgrow.initializer.giveandgrowApplication;
import com.giveandgrow.shared.exception.GiveAndGrowException;
import com.giveandgrow.shared.exception.enums.LocationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = giveandgrowApplication.class)
@AutoConfigureMockMvc
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EventServicePort eventService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateEvent_HappyPath() throws Exception {
        EventDTO dto = new EventDTO();

        doNothing().when(eventService).createEvent(any());

        mockMvc.perform(post("/api/v1/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Event created successfully"));
    }

    @Test
    void testCreateEvent_UnhappyPath() throws Exception {
        EventDTO dto = new EventDTO();

        doThrow(new GiveAndGrowException("Event creation failed", LocationException.GENERAL))
                .when(eventService).createEvent(any());

        mockMvc.perform(post("/api/v1/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Event creation failed"));
    }

    @Test
    void testUpdateEvent_HappyPath() throws Exception {
        EventDTO dto = new EventDTO();

        when(eventService.updateEvent(any())).thenReturn(dto);

        mockMvc.perform(put("/api/v1/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Event updated successfully"));
    }

    @Test
    void testUpdateEvent_UnhappyPath() throws Exception {
        EventDTO dto = new EventDTO();

        when(eventService.updateEvent(any()))
                .thenThrow(new GiveAndGrowException("Event update failed", LocationException.GENERAL));

        mockMvc.perform(put("/api/v1/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Event update failed"));
    }

    @Test
    void testGetAllEventsByUser_HappyPath() throws Exception {
        UUID userId = UUID.randomUUID();
        List<EventDTO> list = Collections.singletonList(new EventDTO());

        when(eventService.getAllEventsByUser(userId)).thenReturn(list);

        mockMvc.perform(get("/api/v1/events/" + userId))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(list)));
    }

    @Test
    void testGetAllEventsByUser_UnhappyPath() throws Exception {
        UUID userId = UUID.randomUUID();

        when(eventService.getAllEventsByUser(userId))
                .thenThrow(new GiveAndGrowException("Events not found", LocationException.GENERAL));

        mockMvc.perform(get("/api/v1/events/" + userId))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("")); // Ajusta según tu controlador
    }

    @Test
    void testGetAllEvents_HappyPath() throws Exception {
        List<EventDTO> list = Collections.singletonList(new EventDTO());

        when(eventService.getAllEvents()).thenReturn(list);

        mockMvc.perform(get("/api/v1/events"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(list)));
    }

    @Test
    void testGetAllEvents_UnhappyPath() throws Exception {
        when(eventService.getAllEvents())
                .thenThrow(new GiveAndGrowException("Error retrieving events", LocationException.GENERAL));

        mockMvc.perform(get("/api/v1/events"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("")); // Ajusta según tu controlador
    }
}
