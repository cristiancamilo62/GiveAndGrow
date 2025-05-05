package com.giveandgrow.domain.ports.input;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.giveandgrow.application.dto.UserDTO;

public interface UserServicePort {

    void createPatient(UserDTO patient);

    Optional<UserDTO> getPatientById(UUID id);

    List<UserDTO> getAllPatients();

    UserDTO  updatePatient(UserDTO patient);

    void deletePatient(UUID id);


}
