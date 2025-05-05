package com.giveandgrow.application.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.giveandgrow.application.dto.OrganizationDTO;
import com.giveandgrow.application.mapper.OrganizationMapperDTO;
import com.giveandgrow.domain.model.organization.OrganizationDomain;
import com.giveandgrow.domain.model.organization.rules.executor.OrganizationValidationsRuleExecutor;
import com.giveandgrow.domain.ports.input.OrganizationServicePort;
import com.giveandgrow.domain.ports.output.OrganizationRepositoryPort;
import com.giveandgrow.shared.validators.structure.GenericValidationDataStructure;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional
public class OrganizationService implements OrganizationServicePort{

    private final OrganizationRepositoryPort organizationRepositoryPort;

    private final OrganizationMapperDTO organizationMapperDTO;

    private final PasswordEncoder passwordEncoder;

    private final OrganizationValidationsRuleExecutor organizationValidationsRuleExecutor; 
    
    private final GenericValidationDataStructure genericValidationDataStructure;

    @Override
    public void createOrganization(OrganizationDTO organization) {

        OrganizationDomain organizationDomain = organizationMapperDTO.toDomain(organization);
    
        organizationDomain.setId(UUID.randomUUID());

        organizationValidationsRuleExecutor.validate(organizationDomain);

        organizationDomain.setPassword(passwordEncoder.encode(organization.getPassword()));

        organizationRepositoryPort.save(organizationDomain);

    }

    @Override
    public Optional<OrganizationDTO> getOrganizationById(UUID id) {

        genericValidationDataStructure.validateDataNotNullOrEmpty(id, "Id Organization");

        return organizationRepositoryPort.findById(id).map(organizationMapperDTO::toDTO);
        
    }

    @Override
    public List<OrganizationDTO> getAllOrganizations() {
      
        return organizationMapperDTO.toDTOList(organizationRepositoryPort.findAll());
    }

    @Override
    public OrganizationDTO updateOrganization(OrganizationDTO organization) {
        
        genericValidationDataStructure.validateDataNotNullOrEmpty(organization.getId(), "Id Organization");

        OrganizationDomain organizationDomain = organizationMapperDTO.toDomain(organization);

        organizationValidationsRuleExecutor.validate(organizationDomain);

        return organizationMapperDTO.toDTO(organizationRepositoryPort.update(organizationDomain));
    }

    @Override
    public void deleteOrganization(UUID id) {
        
        genericValidationDataStructure.validateDataNotNullOrEmpty(id, "Id Organization");

        organizationRepositoryPort.deleteById(id);

    }
}
