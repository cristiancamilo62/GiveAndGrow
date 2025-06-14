package com.giveandgrow.infrastructure.adapters.persistence.mapper;

import com.giveandgrow.domain.model.postulation.PostulationDomain;
import com.giveandgrow.infrastructure.entities.PostulationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostulationMapperEntity {

    PostulationEntity toEntity(PostulationDomain postulationDomain);

    PostulationDomain toDomain(PostulationEntity postulationEntity);
}
