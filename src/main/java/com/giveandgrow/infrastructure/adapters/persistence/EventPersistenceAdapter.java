package com.giveandgrow.infrastructure.adapters.persistence;

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
import com.giveandgrow.shared.helper.ObjectHelper;
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
    @Transactional
    public EventDomain update(EventDomain event, UUID userId) {
        // 1) Cargo el EventEntity que ya vive en el Persistence Context
        EventEntity evEntity = eventJpaRepository.findById(event.getId())
            .orElseThrow(() -> new IdDoesNotExistInDatabase(
                "Event " + MessageCatalog.getContentMessage(MessageCode.M0000016),
                MessageCatalog.getContentMessage(MessageCode.M0000003)
            ));

        // 2) Si quieres también parchear otros campos, los ajustas aquí:
        //    evEntity.setName(event.getName());
        //    evEntity.setStartDateTime(event.getStartDateTime());
        //    … etc.

        // 3) Busco el usuario y lo añado a la lista de la entidad gestionada

        if(!UuidHelper.isDefaultOrNull(userId) || !UuidHelper.isUuidEmpty(userId)) {
            UserEntity uEntity = userJpaRepository.findById(userId)
            .orElseThrow(() -> new IdDoesNotExistInDatabase(
                "User " + MessageCatalog.getContentMessage(MessageCode.M0000016),
                MessageCatalog.getContentMessage(MessageCode.M0000003)
            ));
            evEntity.getUsers().add(uEntity);
            evEntity.setCurrentParticipantsCount(evEntity.getCurrentParticipantsCount() + 1);

        }
        

        // 4) Guardo la MISMA entidad
        EventEntity saved = eventJpaRepository.save(evEntity);

        // 5) La convierto de vuelta a dominio y la retorno
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
    public List<EventDomain> findAllEventsByUser(UUID userId) {
        System.out.println("User ID: " + userId);
        UserEntity userEntity = userJpaRepository.findById(userId)
                .orElseThrow(() -> new IdDoesNotExistInDatabase(
                        "User " + MessageCatalog.getContentMessage(MessageCode.M0000016),
                        MessageCatalog.getContentMessage(MessageCode.M0000003)
                ));
        return eventJpaRepository.findAllByUsers_Id(userEntity.getId()).stream()
                .map(eventMapperEntity::toDomain).toList();
    }

    
}
