package com.giveandgrow.infrastructure.adapters.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.giveandgrow.domain.model.event.EventDomain;
import com.giveandgrow.infrastructure.entities.EventEntity;

@Mapper(componentModel = "spring")
public interface EventMapperEntity {

    @Mapping(target = "organization", ignore = true)
    EventEntity toEntity(EventDomain event);


    @Mapping(target = "organizationId", ignore = true)
    EventDomain toDomain(EventEntity eventEntity);

}
