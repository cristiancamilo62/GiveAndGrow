package com.giveandgrow.infrastructure.adapters.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;
import com.giveandgrow.domain.model.organization.OrganizationDomain;
import com.giveandgrow.domain.ports.output.OrganizationRepositoryPort;
import com.giveandgrow.infrastructure.adapters.persistence.exceptions.IdDoesNotExistInDatabaseException;
import com.giveandgrow.infrastructure.adapters.persistence.mapper.OrganizationMapperEntity;
import com.giveandgrow.infrastructure.entities.OrganizationEntity;
import com.giveandgrow.infrastructure.repositories.OrganizationJpaRepository;
import com.giveandgrow.shared.messages.MessageCatalog;
import com.giveandgrow.shared.messages.enums.MessageCode;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrganizationPersistenceAdapter implements OrganizationRepositoryPort {

    private final OrganizationJpaRepository organizationJpaRepository;
    private final OrganizationMapperEntity organizationMapperEntity;
    private static final String ID_ORGANIZATION = "Organization";

    @Override
    public void save(OrganizationDomain organization) {

        organizationJpaRepository.save(organizationMapperEntity.toEntity(organization));
    }

    @Override
    public OrganizationDomain update(OrganizationDomain organization) {
        if (!this.existsById(organization.getId())) {
            throw new IdDoesNotExistInDatabaseException(
                ID_ORGANIZATION + MessageCatalog.getContentMessage(MessageCode.M0000016),
                MessageCatalog.getContentMessage(MessageCode.M0000003)
            );
        }
        this.save(organization);
        return organization;
    }

    @Override
    public Optional<OrganizationDomain> findById(UUID id) {
       return organizationJpaRepository.findById(id)
                .map(organizationMapperEntity::toDomain);
    }

    @Override
    public List<OrganizationDomain> findAll() {
        return organizationJpaRepository.findAll().stream().map(
            organizationMapperEntity::toDomain).toList();
    }

    @Override
    public List<OrganizationDomain> findAllExample(OrganizationDomain org) {
        var organizationEntity = organizationMapperEntity.toEntity(org);
        
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<OrganizationEntity> example = Example.of(organizationEntity, matcher);

        return organizationMapperEntity.toDomainList(
            organizationJpaRepository.findAll(example));
                   
    }

    @Override
    public void deleteById(UUID id) {

        this.findById(id).
        ifPresent(organization -> organizationJpaRepository.delete(organizationMapperEntity.toEntity(organization)));

    }

    @Override
    public boolean existsById(UUID id) {
        return organizationJpaRepository.existsById(id);
    }

    @Override
    public boolean existsByEmail(String email, UUID id) {
        
        return organizationJpaRepository.existsByEmailAndIdNot(email,id);
    }

    @Override
    public boolean existsByEmailAndPassword(String user, String password) {
        
        return organizationJpaRepository.existsByEmailAndPassword(user, password);
    }

    @Override
    public Optional<OrganizationDomain> findByEmail(String email) {
        
        return organizationJpaRepository.findByEmail(email)
                .map(organizationMapperEntity::toDomain);
    }

    @Override
    public boolean existsByContactNumber(String contactNumber,UUID id) {
        
        return organizationJpaRepository.existsByContactNumberAndIdNot(contactNumber,id);
    }
}
