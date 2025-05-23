package com.giveandgrow.domain.ports.input;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.giveandgrow.application.dto.UserDTO;

public interface UserServicePort {

    void createUser(UserDTO patient);

    Optional<UserDTO> getUserById(UUID id);

    List<UserDTO> getAllUsers();

    UserDTO  updateUser(UserDTO patient);

    void deleteUser(UUID id);


}
