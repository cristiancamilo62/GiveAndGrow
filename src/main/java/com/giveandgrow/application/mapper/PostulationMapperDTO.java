package com.giveandgrow.application.mapper;

import com.giveandgrow.application.dto.PostulationDTO;
import com.giveandgrow.domain.model.postulation.PostulationDomain;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostulationMapperDTO {

    PostulationDTO toDTO(PostulationDomain postulationDomain);

    PostulationDomain toDomain(PostulationMapperDTO postulationDTO);

    List<PostulationDomain> toDomainList(List<PostulationDTO> postulations);

    List<PostulationDTO> toDTOList(List<PostulationDomain> postulationDomains);
}
