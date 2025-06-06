package com.giveandgrow.domain.ports.output;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.giveandgrow.domain.model.organization.OrganizationDomain;

public interface OrganizationRepositoryPort {

    void save(OrganizationDomain organization);

    OrganizationDomain update(OrganizationDomain organization);

    Optional<OrganizationDomain> findById(UUID id);

    List<OrganizationDomain> findAll();

    List<OrganizationDomain> findAllExample(OrganizationDomain org);

    void deleteById(UUID id);

    boolean existsById(UUID id);

    boolean existsByContactNumber(String contactNumber,UUID id);

    boolean existsByEmail(String email, UUID id);

    boolean existsByEmailAndPassword(String user, String password);

    Optional<OrganizationDomain> findByEmail(String email);
}
