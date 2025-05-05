package com.giveandgrow.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.giveandgrow.application.dto.OrganizationDTO;
import com.giveandgrow.domain.model.organization.OrganizationDomain;

@Mapper(componentModel = "spring")
public interface OrganizationMapperDTO {

    
    OrganizationDTO toDTO(OrganizationDomain organizationDomain);

    @Mapping(target = "events", ignore = true)
    OrganizationDomain toDomain(OrganizationDTO organizationDTO);

    List<OrganizationDTO> toDTOList(List<OrganizationDomain> organizationDomains);

    List<OrganizationDomain> toDomainList(List<OrganizationDTO> organizationDTOs);

}
