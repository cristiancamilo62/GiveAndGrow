package com.giveandgrow.domain.ports.input;


import com.giveandgrow.application.dto.UserDTO;
import com.giveandgrow.domain.model.user.UserDomain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserServicePort {

    void createPatient(UserDomain patient);

    Optional<UserDTO> getPatientById(UUID id);

    List<UserDTO> getAllPatients();

    void updatePatient(UserDomain patient);

    void deletePatient(UUID id);

}
