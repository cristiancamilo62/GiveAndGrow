package com.giveandgrow.infrastructure.adapters.persistence;

import com.giveandgrow.domain.model.user.UserDomain;
import com.giveandgrow.domain.ports.output.UserRepositoryPort;
import com.giveandgrow.infrastructure.adapters.persistence.exceptions.IdDoesNotExistInDatabaseException;
import com.giveandgrow.infrastructure.adapters.persistence.mapper.UserMapperEntity;
import com.giveandgrow.infrastructure.entities.UserEntity;
import com.giveandgrow.infrastructure.repositories.UserJpaRepository;
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

    private final UserJpaRepository userJpaRepository;
    private final UserMapperEntity userMapperEntity;
    private static final String ID_USER = "User";

    @Override
    public void save(UserDomain user) {

        UserEntity userEntity = userMapperEntity.toEntity(user);
        userJpaRepository.save(userEntity);
    }

    @Override
    public UserDomain update(UserDomain user) {

        if (!this.existsById(user.getId())){
            throw new IdDoesNotExistInDatabaseException(
                    ID_USER + MessageCatalog.getContentMessage(MessageCode.M0000016),
                    MessageCatalog.getContentMessage(MessageCode.M0000003)
            );
        }
        this.save(user);
        return user;

    }


    @Override
    public Optional<UserDomain> findById(UUID id) {

        return userJpaRepository.findById(id)
                .map(userMapperEntity::toDomain)
                .or(() -> {
                    throw new IdDoesNotExistInDatabaseException(
                            MessageCatalog.getContentMessage(MessageCode.M0000016),
                            MessageCatalog.getContentMessage(MessageCode.M0000003)
                    );
                });
    }

    @Override
    public List<UserDomain> findAll() {
        return userJpaRepository.findAll().stream()
                .map(userMapperEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<UserDomain> findAllExample(UserDomain user) {

        var userEntity = userMapperEntity.toEntity(user);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<UserEntity> example = Example.of(userEntity, matcher);

        return userMapperEntity.toListDomain(userJpaRepository.findAll(example));

    }

    @Override
    public void deleteById(UUID id) {
        this.findById(id).ifPresent(user ->{
            user.setAccountStatement(false);

            this.save(user);

        });
    }

    @Override
    public boolean existsById(UUID id) {
        return userJpaRepository.existsById(id);
    }

    @Override
    public boolean  existsByIdentification(String identification,UUID id) {
        return userJpaRepository.existsByIdentificationAndIdNot(identification,id);
    }

    @Override
    public boolean existsByEmail(String email,UUID id) {
        return userJpaRepository.existsByEmailAndIdNot(email,id);
    }

    @Override
    public boolean existsByEmailAndPassword(String user, String password) {
        return userJpaRepository.existsByEmailAndPassword(user, password);
    }

    @Override
    public Optional<UserDomain> findByEmail(String email) {

        return userJpaRepository.findByEmail(email).map(userMapperEntity::toDomain);
    }
}
