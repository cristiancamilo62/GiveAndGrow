package com.giveandgrow.domain.model.organization.rules.validation;

import org.springframework.stereotype.Service;

import com.giveandgrow.domain.model.GenericValidationRule;
import com.giveandgrow.shared.validators.structure.GenericValidationDataStructure;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmailOrganizationRuleValidator implements GenericValidationRule<String> {

    public static final String FIELD_NAME_EMAIL = "Email ";
    private final GenericValidationDataStructure genericValidationDataStructure;

    @Override
    public void validate(String email) {

        genericValidationDataStructure.validateDataNotNullOrEmpty(email, FIELD_NAME_EMAIL);

       genericValidationDataStructure.validateLengthDataRange(email, 11, 40, FIELD_NAME_EMAIL);

        genericValidationDataStructure.validateFormatDataOnlyLettersAndDigitsAtDomain(email, FIELD_NAME_EMAIL);


    }
}
