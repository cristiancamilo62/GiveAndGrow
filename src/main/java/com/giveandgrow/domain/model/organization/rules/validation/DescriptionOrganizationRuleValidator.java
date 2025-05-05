package com.giveandgrow.domain.model.organization.rules.validation;

import org.springframework.stereotype.Service;

import com.giveandgrow.domain.model.GenericValidationRule;
import com.giveandgrow.shared.validators.structure.GenericValidationDataStructure;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DescriptionOrganizationRuleValidator implements GenericValidationRule<String> {

    public static final String FIELD_NAME_DESCRIPTION = "Description ";
    private final GenericValidationDataStructure genericValidationDataStructure;

    @Override
    public void validate(String description) {

        genericValidationDataStructure.validateDataNotNullOrEmpty(description, FIELD_NAME_DESCRIPTION);

        genericValidationDataStructure.validateLengthDataRange(description, 10, 200, FIELD_NAME_DESCRIPTION);

        genericValidationDataStructure.validateFormatDataOnlyLettersAndDigitsAtSpace(description, FIELD_NAME_DESCRIPTION);
       
        }
}
