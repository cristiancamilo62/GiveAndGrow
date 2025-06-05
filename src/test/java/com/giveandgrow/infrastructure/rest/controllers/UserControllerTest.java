package com.giveandgrow.infrastructure.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.giveandgrow.application.dto.UserDTO;
import com.giveandgrow.domain.ports.input.UserServicePort;
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
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = giveandgrowApplication.class)
@AutoConfigureMockMvc
@WithMockUser
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServicePort userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testRegisterUser_HappyPath() throws Exception {
        UserDTO userDTO = new UserDTO();

        mockMvc.perform(post("/api/v1/users/r")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().string("User created successfully"));
    }

    @Test
    void testRegisterUser_UnhappyPath() throws Exception {
        UserDTO userDTO = new UserDTO();

        doThrow(new GiveAndGrowException("Registration failed", LocationException.GENERAL))
                .when(userService).createUser(any());

        mockMvc.perform(post("/api/v1/users/r")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Registration failed"));
    }

    @Test
    void testGetUserById_HappyPath() throws Exception {
        UUID userId = UUID.randomUUID();
        when(userService.getUserById(userId)).thenReturn(Optional.of(new UserDTO()));

        mockMvc.perform(get("/api/v1/users/" + userId))
                .andExpect(status().isOk());
    }

    @Test
    void testGetUserById_UnhappyPath() throws Exception {
        UUID userId = UUID.randomUUID();
        when(userService.getUserById(userId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/users/" + userId))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteUser_HappyPath() throws Exception {
        UUID userId = UUID.randomUUID();

        mockMvc.perform(delete("/api/v1/users/" + userId))
                .andExpect(status().isOk())
                .andExpect(content().string("Your account has been deleted successfully"));
    }

    @Test
    void testDeleteUser_UnhappyPath() throws Exception {
        UUID userId = UUID.randomUUID();
        doThrow(new GiveAndGrowException("User not found", LocationException.GENERAL))
                .when(userService).deleteUser(userId);

        mockMvc.perform(delete("/api/v1/users/" + userId))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("User not found"));
    }

    @Test
    void testGetAllUsers_HappyPath() throws Exception {
        when(userService.getAllUsers()).thenReturn(Collections.singletonList(new UserDTO()));

        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllUsers_UnhappyPath() throws Exception {
        when(userService.getAllUsers()).thenThrow(new GiveAndGrowException("Error retrieving users", LocationException.GENERAL));

        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isInternalServerError());
    }
}
