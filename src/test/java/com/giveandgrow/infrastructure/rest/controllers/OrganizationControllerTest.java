package com.giveandgrow.infrastructure.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.giveandgrow.application.dto.OrganizationDTO;
import com.giveandgrow.domain.ports.input.OrganizationServicePort;
import com.giveandgrow.initializer.giveandgrowApplication;
import com.giveandgrow.shared.exception.GiveAndGrowException;
import com.giveandgrow.shared.exception.enums.LocationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = giveandgrowApplication.class)
@AutoConfigureMockMvc
public class OrganizationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrganizationServicePort organizationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateOrganization_HappyPath() throws Exception {
        OrganizationDTO dto = new OrganizationDTO();

        // Si tu método createOrganization no retorna nada y no lanza excepción, no necesitas stub.

        mockMvc.perform(post("/api/v1/organizations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Organization created successfully"));
    }

    @Test
    void testCreateOrganization_UnhappyPath() throws Exception {
        OrganizationDTO dto = new OrganizationDTO();

        doThrow(new GiveAndGrowException("Creation failed", LocationException.GENERAL))
                .when(organizationService).createOrganization(any());

        mockMvc.perform(post("/api/v1/organizations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Creation failed"));
    }
}
