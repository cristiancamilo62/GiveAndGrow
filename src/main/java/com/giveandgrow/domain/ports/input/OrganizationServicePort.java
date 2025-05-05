package com.giveandgrow.domain.ports.input;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.giveandgrow.application.dto.OrganizationDTO;

public interface OrganizationServicePort {

    void createOrganization(OrganizationDTO organization);

    Optional<OrganizationDTO> getOrganizationById(UUID id);

    List<OrganizationDTO> getAllOrganizations();

    OrganizationDTO updateOrganization(OrganizationDTO organization);

    void deleteOrganization(UUID id);
}
