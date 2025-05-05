package com.giveandgrow.domain.model.organization.rules.business;

import org.springframework.stereotype.Component;

import com.giveandgrow.domain.model.GenericValidationRule;
import com.giveandgrow.domain.model.organization.exceptions.OrganizationContactNumberAlreadyExistException;
import com.giveandgrow.domain.ports.output.OrganizationRepositoryPort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class OrganizationContactNumberAlreadyExistBusinessRule implements GenericValidationRule<String> {

    private final OrganizationRepositoryPort organizationRepositoryPort;

    @Override
    public void validate(String contactNumber) {
        if (organizationRepositoryPort.existsByContactNumber(contactNumber)) {
            throw new OrganizationContactNumberAlreadyExistException();
        }
       
    }


    
}
