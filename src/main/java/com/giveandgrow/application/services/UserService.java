package com.giveandgrow.application.services;

import com.giveandgrow.application.dto.UserDTO;
import com.giveandgrow.application.mapper.UserMapperDTO;
import com.giveandgrow.domain.model.user.UserDomain;
import com.giveandgrow.domain.model.user.rules.executor.UserValidationsRuleExecutor;
import com.giveandgrow.domain.ports.input.UserServicePort;
import com.giveandgrow.domain.ports.output.UserRepositoryPort;
import com.giveandgrow.shared.validators.structure.GenericValidationDataStructure;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserServicePort {

    private final UserRepositoryPort userRepositoryPort;

    private final UserValidationsRuleExecutor validationsRuleExecutor;

    private final UserMapperDTO userMapperDTO;

    private final PasswordEncoder passwordEncoder;

    private final GenericValidationDataStructure genericValidationDataStructure;


    @Override
    public void createPatient(UserDTO userDTO) {

        UserDomain userDomain = userMapperDTO.toDomain(userDTO);

        userDomain.setId(UUID.randomUUID());

        validationsRuleExecutor.validate(userDomain);

        userDomain.setPassword(passwordEncoder.encode(userDomain.getPassword()));

        userRepositoryPort.save(userDomain);
    }

    @Override
    public Optional<UserDTO> getPatientById(UUID id) {

        genericValidationDataStructure.validateDataNotNullOrEmpty(id,"Id User");

        return userRepositoryPort.findById(id).map(userMapperDTO::toDTO);
    }

    @Override
    public List<UserDTO> getAllPatients() {

        return userMapperDTO.toDTOList(userRepositoryPort.findAll());
    }

    @Override
    public UserDTO updatePatient(UserDTO userDTO) {

        UserDomain userDomain = userMapperDTO.toDomain(userDTO);

        genericValidationDataStructure.validateDataNotNullOrEmpty(userDomain.getId(),"Id User");

        validationsRuleExecutor.validate(userDomain);

        return userMapperDTO.toDTO(userRepositoryPort.update(userDomain));

    }

    @Override
    public void deletePatient(UUID id) {

        genericValidationDataStructure.validateDataNotNullOrEmpty(id,"Id User");

        userRepositoryPort.deleteById(id);

    }

}
