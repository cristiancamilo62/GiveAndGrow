package com.giveandgrow.domain.model.organization.rules.validation;

import com.giveandgrow.domain.model.GenericValidationRule;
import com.giveandgrow.shared.validators.structure.GenericValidationDataStructure;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NITOrganizationRuleValidator implements GenericValidationRule<String> {

    public static final String FIELD_NAME_NIT = "NIT Organization";

    private final GenericValidationDataStructure genericValidationDataStructure;

    @Override
    public void validate(String nit) {

        genericValidationDataStructure.validateDataNotNullOrEmpty(nit, FIELD_NAME_NIT);

        genericValidationDataStructure.validateLengthDataRange(nit,10,15,FIELD_NAME_NIT);

        genericValidationDataStructure.validateFormatDataOnlyDigits(nit,FIELD_NAME_NIT);

    }
}
