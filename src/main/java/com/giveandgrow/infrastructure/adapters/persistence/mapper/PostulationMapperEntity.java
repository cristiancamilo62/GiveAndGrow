package com.giveandgrow.infrastructure.adapters.persistence.mapper;

import com.giveandgrow.domain.model.postulation.PostulationDomain;
import com.giveandgrow.infrastructure.entities.EventEntity;
import com.giveandgrow.infrastructure.entities.PostulationEntity;
import com.giveandgrow.infrastructure.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface PostulationMapperEntity {

    @Mapping(target = "user", source = "userId", qualifiedByName = "uuidToUserEntity")
    @Mapping(target = "event", source = "eventId", qualifiedByName = "uuidToEventEntity")
    PostulationEntity toEntity(PostulationDomain postulationDomain);

    @Mapping(target = "userId", source = "user", qualifiedByName = "userEntityToUuid")
    @Mapping(target = "eventId", source = "event", qualifiedByName = "eventEntityToUuid")
    PostulationDomain toDomain(PostulationEntity postulationEntity);

    // Métodos auxiliares para convertir UUID a Entity
    @Named("uuidToUserEntity")
    default UserEntity uuidToUserEntity(UUID userId) {
        if (userId == null) return null;
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);  // Suponiendo que solo asignamos el UUID, si necesitas más información, consulta la BD.
        return userEntity;
    }

    @Named("uuidToEventEntity")
    default EventEntity uuidToEventEntity(UUID eventId) {
        if (eventId == null) return null;
        EventEntity eventEntity = new EventEntity();
        eventEntity.setId(eventId);  // Similar a lo anterior, solo asignamos el UUID.
        return eventEntity;
    }

    // Métodos auxiliares para convertir Entity a UUID
    @Named("userEntityToUuid")
    default UUID userEntityToUuid(UserEntity userEntity) {
        return userEntity != null ? userEntity.getId() : null;
    }

    @Named("eventEntityToUuid")
    default UUID eventEntityToUuid(EventEntity eventEntity) {
        return eventEntity != null ? eventEntity.getId() : null;
    }
}
