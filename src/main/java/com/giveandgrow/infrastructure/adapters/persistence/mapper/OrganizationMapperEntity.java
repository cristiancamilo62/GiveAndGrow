package com.giveandgrow.infrastructure.adapters.persistence.mapper;

import java.util.List;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.giveandgrow.domain.model.organization.OrganizationDomain;
import com.giveandgrow.infrastructure.entities.OrganizationEntity;

@Component
@Mapper(
        componentModel = "spring",
        uses = EventMapperEntity.class
)
public abstract class OrganizationMapperEntity {

    @Autowired
    protected EventMapperEntity eventMapper;

    /** Map Entity -> Domain, incluyendo conversión de role */
    @Mapping(target = "events", ignore = true)
    public abstract OrganizationDomain toDomain(OrganizationEntity entity);

    @AfterMapping
    protected void populateDomainEvents(OrganizationEntity entity,
                                        @MappingTarget OrganizationDomain domain) {
        if (entity.getEvents() != null) {
            entity.getEvents().stream()
                    .map(eventMapper::toDomain)
                    .forEach(domain::addEvent);
        }
    }

    /** Map Domain -> Entity, incluyendo conversión de role */
    @Mapping(target = "events", ignore = true)
    public abstract OrganizationEntity toEntity(OrganizationDomain domain);

    @AfterMapping
    protected void populateEntityEvents(OrganizationDomain domain,
                                        @MappingTarget OrganizationEntity entity) {
        if (domain.getEvents() != null) {
            domain.getEvents().forEach(ev -> {
                var evEntity = eventMapper.toEntity(ev);
                evEntity.setOrganization(entity);
                entity.getEvents().add(evEntity);
            });
        }
    }

    /** Map a list of entities */
    public abstract List<OrganizationDomain> toDomainList(List<OrganizationEntity> entities);
}
