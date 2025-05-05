package com.giveandgrow.domain.model.organization.rules.validation;

import org.springframework.stereotype.Service;

import com.giveandgrow.domain.model.GenericValidationRule;
import com.giveandgrow.shared.validators.structure.GenericValidationDataStructure;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PasswordOrganizationRuleValidator implements GenericValidationRule<String> {

    public static final String FIELD_NAME_PASSWORD = "Password Organization ";
    private final GenericValidationDataStructure genericValidationDataStructure;

    @Override
    public void validate(String password) {

        genericValidationDataStructure.validateDataNotNullOrEmpty(password, FIELD_NAME_PASSWORD);

        genericValidationDataStructure.validateLengthDataRange(password, 8, 20, FIELD_NAME_PASSWORD);

        genericValidationDataStructure.validateFormatPassword(password, FIELD_NAME_PASSWORD);

    }
}
