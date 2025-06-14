package com.giveandgrow.infrastructure.rest.controllers;

import com.giveandgrow.application.dto.UserDTO;
import com.giveandgrow.domain.model.user.UserDomain;
import com.giveandgrow.domain.ports.input.UserServicePort;
import com.giveandgrow.infrastructure.rest.response.Response;
import com.giveandgrow.shared.exception.GiveAndGrowException;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
    public ResponseEntity<Response<Void>> register(@RequestBody UserDTO userDTO) {
        UserDTO userSaved = userService.createUser(userDTO);
        Response<Void> response = Response.success(HttpStatus.CREATED, "User created successfully");
        return ResponseEntity.created(URI.create("/api/v1/users/" + userSaved.getId())).body(response);
    }


    @PutMapping
    public ResponseEntity<Response<UserDTO>> update(@RequestBody UserDTO userDTO) {
        UserDTO userUpdate =  userService.updateUser(userDTO);
        Response<UserDTO> response = Response.of(HttpStatus.OK, List.of(userUpdate), "User updated successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<Response<Void>> delete(@PathVariable UUID idUser) {
        userService.deleteUser(idUser);
        Response<Void> response = Response.success(HttpStatus.NO_CONTENT, "User deleted successfully");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<Response<UserDTO>> getUserById(@PathVariable UUID idUser) {
        Optional<UserDTO> user = userService.getUserById(idUser);
        Response<UserDTO> response = Response.of(HttpStatus.OK, List.of(user.get()), "User found successfully");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllPatients() {

        try{

            List<UserDTO> users = userService.getAllUsers();

            return ResponseEntity.status(HttpStatus.OK).body(users);

        }catch (GiveAndGrowException exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }

    }

}
