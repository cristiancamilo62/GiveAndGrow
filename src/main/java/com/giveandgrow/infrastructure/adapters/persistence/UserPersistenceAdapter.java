package com.giveandgrow.infrastructure.adapters.persistence;

import com.giveandgrow.domain.model.user.UserDomain;
import com.giveandgrow.domain.ports.output.UserRepositoryPort;
import com.giveandgrow.infrastructure.adapters.persistence.exceptions.UserIdDoesNotExistInDatabase;
import com.giveandgrow.infrastructure.adapters.persistence.mapper.UserMapperEntity;
import com.giveandgrow.infrastructure.entities.UserEntity;
import com.giveandgrow.infrastructure.repositories.PatientJpaRepository;
import com.giveandgrow.shared.messages.MessageCatalog;
import com.giveandgrow.shared.messages.enums.MessageCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserRepositoryPort {

    private final PatientJpaRepository patientJpaRepository;

    private final UserMapperEntity patientMapperEntity;

    @Override
    public void save(UserDomain patient) {

        UserEntity patientEntity = patientMapperEntity.toEntity(patient);

        patientJpaRepository.save(patientEntity);
    }
    @Override
    public Optional<UserDomain> findById(UUID id) {
        System.out.println(id);
        return patientJpaRepository.findById(id)
                .map(patientMapperEntity::toDomain)
                .or(() -> {
                    throw new UserIdDoesNotExistInDatabase(
                            MessageCatalog.getContentMessage(MessageCode.M0000016),
                            MessageCatalog.getContentMessage(MessageCode.M0000003)
                    );
                });
    }

    @Override
    public List<UserDomain> findAll() {
        return patientJpaRepository.findAll().stream()
                .map(patientMapperEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<UserDomain> findAllExample(UserDomain patient) {

        var patientEntity = patientMapperEntity.toEntity(patient);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<UserEntity> example = Example.of(patientEntity, matcher);

        return patientMapperEntity.toListDomain(patientJpaRepository.findAll(example));

    }

    @Override
    public void deleteById(UUID id) {
        patientJpaRepository.findById(id).ifPresent(patient ->{
            patient.setAccountStatement(false);
            patientJpaRepository.save(patient);
        });
    }

    @Override
    public boolean existsById(UUID id) {
        return patientJpaRepository.existsById(id);
    }

    @Override
    public Optional<UserDomain> findByIdentification(String identification) {

        return  patientJpaRepository.findByIdentification(identification).map(patientMapperEntity::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return patientJpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByEmailAndPassword(String user, String password) {
        return patientJpaRepository.existsByEmailAndPassword(user, password);
    }

    @Override
    public Optional<UserDomain> findByEmail(String email) {

        return patientJpaRepository.findByEmail(email).map(patientMapperEntity::toDomain);
    }
}
