package com.giveandgrow.infrastructure.rest.controllers;

import com.giveandgrow.application.dto.UserDTO;
import com.giveandgrow.domain.ports.input.UserServicePort;
import com.giveandgrow.shared.exception.GiveAndGrowException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserServicePort userService;

    @GetMapping("/dummy")
    public UserDTO obtain() {
        return new UserDTO();
    }

    @PostMapping("/r")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        try {
            userService.createPatient(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Your account has been created successfully");
        } catch (GiveAndGrowException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody UserDTO userDTO) {

        try {

            userService.updatePatient(userDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Your account has been updated successfully");

        } catch (GiveAndGrowException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<String> delete(@PathVariable UUID idUser) {

        try {
            userService.deletePatient(idUser);
            return ResponseEntity.status(HttpStatus.OK).body("Your account has been deleted successfully");

        } catch (GiveAndGrowException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID idUser) {

        try {
            Optional<UserDTO> user = userService.getPatientById(idUser);

            return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null));

        } catch (GiveAndGrowException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);  // Retorna 500 si ocurre una excepción interna
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllPatients() {

        try{

            List<UserDTO> users = userService.getAllPatients();

            return ResponseEntity.status(HttpStatus.OK).body(users);

        }catch (GiveAndGrowException exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }

    }

}
