package com.giveandgrow.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.giveandgrow.application.dto.EventDTO;
import com.giveandgrow.domain.model.event.EventDomain;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapperDTO {

    @Mapping(source = "address", target = "address")
    EventDTO toDTO(EventDomain eventDomain);

    @Mapping(source = "address", target = "address")
    EventDomain toDomain(EventDTO eventDTO);

    List<EventDTO> toDTOList(List<EventDomain> eventDomains);

}
