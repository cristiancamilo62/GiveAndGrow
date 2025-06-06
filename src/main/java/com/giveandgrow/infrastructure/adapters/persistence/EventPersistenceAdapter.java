package com.giveandgrow.infrastructure.adapters.persistence;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;
import com.giveandgrow.domain.model.event.EventDomain;
import com.giveandgrow.domain.ports.output.EventRepositoryPort;
import com.giveandgrow.infrastructure.adapters.persistence.exceptions.IdDoesNotExistInDatabase;
import com.giveandgrow.infrastructure.adapters.persistence.mapper.EventMapperEntity;
import com.giveandgrow.infrastructure.entities.EventEntity;
import com.giveandgrow.infrastructure.entities.OrganizationEntity;
import com.giveandgrow.infrastructure.entities.UserEntity;
import com.giveandgrow.infrastructure.repositories.EventJpaRepository;
import com.giveandgrow.infrastructure.repositories.OrganizationJpaRepository;
import com.giveandgrow.infrastructure.repositories.UserJpaRepository;
import com.giveandgrow.shared.helper.UuidHelper;
import com.giveandgrow.shared.messages.MessageCatalog;
import com.giveandgrow.shared.messages.enums.MessageCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EventPersistenceAdapter implements EventRepositoryPort{

    private final EventJpaRepository eventJpaRepository;
    private final OrganizationJpaRepository organizationJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final EventMapperEntity eventMapperEntity;
    private static final String ID_EVENT = "Event";

    @Override
    @Transactional
    public void save(EventDomain event) {

        // 1) Carga la organización (o lanza si no existe)
    OrganizationEntity org = organizationJpaRepository
      .findById(event.getOrganizationId())
      .orElseThrow(() -> new IdDoesNotExistInDatabase(
          "Organization " + MessageCatalog.getContentMessage(MessageCode.M0000016),
          MessageCatalog.getContentMessage(MessageCode.M0000003)
      ));

        // 2) Mappea el dominio al entity y fija la relación
        EventEntity evEntity = eventMapperEntity.toEntity(event);
        evEntity.setOrganization(org);

        // 3) Añade el evento al agregado de la organización
        org.getEvents().add(evEntity);

        // 4) Persiste la organización (cascade ALL propagará el save del evento)
        organizationJpaRepository.save(org);
        
    }

    @Override
    public EventDomain update(EventDomain event, UUID userId) {
        // 1) Cargar la entidad existente desde la base de datos
        EventEntity evEntity = eventJpaRepository.findById(event.getId())
                .orElseThrow(() -> new IdDoesNotExistInDatabase(
                        "Event " + MessageCatalog.getContentMessage(MessageCode.M0000016),
                        MessageCatalog.getContentMessage(MessageCode.M0000003)
                ));

        // 2) Actualizar los campos del evento con los nuevos datos del objeto EventDomain
        evEntity.setName(event.getName());
        evEntity.setStartDateTime(event.getStartDateTime());
        evEntity.setRegistrationDeadline(event.getRegistrationDeadline());
        evEntity.setDescription(event.getDescription());
        evEntity.setLocation(event.getLocation());
        evEntity.setAddress(event.getAddress());
        evEntity.setMaxParticipants(event.getMaxParticipants());
        evEntity.setCategory(event.getCategory());
        // Agrega aquí cualquier otro campo que quieras actualizar

        // 3) Validar y agregar usuario si se proporcionó un userId válido
        boolean isUserIdValid = userId != null
                && !UuidHelper.isDefaultOrNull(userId)
                && !UuidHelper.isUuidEmpty(userId);

        if (isUserIdValid) {
            UserEntity uEntity = userJpaRepository.findById(userId)
                    .orElseThrow(() -> new IdDoesNotExistInDatabase(
                            "User " + MessageCatalog.getContentMessage(MessageCode.M0000016),
                            MessageCatalog.getContentMessage(MessageCode.M0000003)
                    ));

            evEntity.getUsers().add(uEntity);
            evEntity.setCurrentParticipantsCount(evEntity.getCurrentParticipantsCount() + 1);
        }

        // 4) Guardar la entidad gestionada (evEntity)
        EventEntity saved = eventJpaRepository.save(evEntity);

        // 5) Convertir a dominio y retornar
        return eventMapperEntity.toDomain(saved);
    }




    @Override
    public void delete(UUID id) {
        if (!this.existsById(id)) {
            throw new IdDoesNotExistInDatabase(
                ID_EVENT + MessageCatalog.getContentMessage(MessageCode.M0000016),
                MessageCatalog.getContentMessage(MessageCode.M0000003));
        }
        eventJpaRepository.deleteById(id);
    }
    @Override
    public List<EventDomain> findAll() {
        return eventJpaRepository.findAll().stream()
                .map(eventMapperEntity::toDomain).toList();
    }
    @Override
    public boolean existsById(UUID id) {
        return eventJpaRepository.existsById(id);
    }

    @Override
    public List<EventDomain> findByOrganizationId(UUID organizationId) {
        return eventJpaRepository.findAllByOrganization_Id(organizationId).stream()
                .map(eventMapperEntity::toDomain).toList();
    }

    @Override
    public Optional<EventDomain> findById(UUID id) {
        return eventJpaRepository.findById(id)
                .map(eventMapperEntity::toDomain)
                .or(() -> {
                    throw new IdDoesNotExistInDatabase(
                            ID_EVENT + MessageCatalog.getContentMessage(MessageCode.M0000016),
                            MessageCatalog.getContentMessage(MessageCode.M0000003)
                    );
                });
    }

    @Override
    public List<EventDomain> findByCategory(String category) {

        return eventMapperEntity.toDomainList(eventJpaRepository.findAllByCategory(category));
    }

    @Override
    public List<EventDomain> findAllEventsByUser(UUID userId) {
        UserEntity userEntity = userJpaRepository.findById(userId)
                .orElseThrow(() -> new IdDoesNotExistInDatabase(
                        "User " + MessageCatalog.getContentMessage(MessageCode.M0000016),
                        MessageCatalog.getContentMessage(MessageCode.M0000003)
                ));
        return eventJpaRepository.findAllByUsers_Id(userEntity.getId()).stream()
                .map(eventMapperEntity::toDomain).toList();
    }

    @Override
    public List<EventDomain> findByLocationAndStartDateTimeAndCategory(String location, LocalDateTime startDateTime,
           String category) {


        return eventJpaRepository.findAllByLocationAndStartDateTimeAndCategory(location, startDateTime, category)
                .stream().map(eventMapperEntity::toDomain).toList();
    }

    
}
