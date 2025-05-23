package com.giveandgrow.domain.ports.output;

import com.giveandgrow.domain.model.user.UserDomain;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort {

    void save(UserDomain user);

    UserDomain update(UserDomain user);

    Optional<UserDomain> findById(UUID id);

    List<UserDomain> findAll();

    List<UserDomain> findAllExample(UserDomain user);

    void deleteById(UUID id);

    boolean existsById(UUID id);

    Optional<UserDomain> findByIdentification(String identification);

    boolean existsByEmail(String email);

    boolean existsByEmailAndPassword(String user, String password);

    Optional<UserDomain> findByEmail(String email);

}
