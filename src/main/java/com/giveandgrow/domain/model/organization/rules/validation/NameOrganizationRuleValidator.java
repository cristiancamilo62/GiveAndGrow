package com.giveandgrow.domain.model.organization.rules.validation;

import org.springframework.stereotype.Service;

import com.giveandgrow.domain.model.GenericValidationRule;
import com.giveandgrow.shared.validators.structure.GenericValidationDataStructure;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NameOrganizationRuleValidator implements GenericValidationRule<String> {

    public static final String FIELD_NAME_NAME_ORGANIZATION = "Name Organization ";

    private final GenericValidationDataStructure genericValidationDataStructure;

    @Override
    public void validate(String name) {
        
        genericValidationDataStructure.validateDataNotNullOrEmpty(name, FIELD_NAME_NAME_ORGANIZATION);

        genericValidationDataStructure.validateLengthDataRange(name, 3, 60, FIELD_NAME_NAME_ORGANIZATION);

        genericValidationDataStructure.validateFormatDataOnlyLettersAndDigitsAtSpace(name, FIELD_NAME_NAME_ORGANIZATION);
    }

    
}
