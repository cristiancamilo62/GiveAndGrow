package com.giveandgrow.domain.model.organization.rules.business;

import com.giveandgrow.domain.model.organization.OrganizationDomain;
import org.springframework.stereotype.Component;

import com.giveandgrow.domain.model.GenericValidationRule;
import com.giveandgrow.domain.model.organization.exceptions.OrganizationContactNumberAlreadyExistException;
import com.giveandgrow.domain.ports.output.OrganizationRepositoryPort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class OrganizationContactNumberAlreadyExistBusinessRule implements GenericValidationRule<OrganizationDomain> {

    private final OrganizationRepositoryPort organizationRepositoryPort;

    @Override
    public void validate(OrganizationDomain domain) {
        if (organizationRepositoryPort.existsByContactNumber(domain.getContactNumber(), domain.getId())) {
            throw new OrganizationContactNumberAlreadyExistException();
        }
       
    }


    
}
