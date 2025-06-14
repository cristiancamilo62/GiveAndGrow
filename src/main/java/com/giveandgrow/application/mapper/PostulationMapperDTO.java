package com.giveandgrow.application.mapper;

import com.giveandgrow.domain.model.postulation.PostulationDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostulationMapperDTO {

    PostulationMapperDTO toDTO(PostulationDomain postulationDomain);

    PostulationDomain toDomain(PostulationMapperDTO postulationDTO);
}
