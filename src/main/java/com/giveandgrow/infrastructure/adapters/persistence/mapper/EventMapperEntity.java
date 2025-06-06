package com.giveandgrow.infrastructure.adapters.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.giveandgrow.domain.model.event.EventDomain;
import com.giveandgrow.infrastructure.entities.EventEntity;

@Mapper(componentModel = "spring")
public interface EventMapperEntity {

    @Mapping(target = "organization", ignore = true)
    @Mapping(source = "address", target = "address")
    EventEntity toEntity(EventDomain event);


    @Mapping(target = "organizationId", ignore = true)
    @Mapping(source = "address", target = "address")
    EventDomain toDomain(EventEntity eventEntity);

    List<EventDomain> toDomainList(List<EventEntity> eventEntities);

    List<EventEntity> toEntityList(List<EventDomain> eventDomains);

}
