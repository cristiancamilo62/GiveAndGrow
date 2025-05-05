package com.giveandgrow.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.giveandgrow.application.dto.EventDTO;
import com.giveandgrow.domain.model.event.EventDomain;

@Mapper(componentModel = "spring")
public interface EventMapperDTO {

    EventDTO toDTO(EventDomain eventDomain);

    EventDomain toDomain(EventDTO eventDTO);

    List<EventDTO> toDTOList(List<EventDomain> eventDomains);

}
