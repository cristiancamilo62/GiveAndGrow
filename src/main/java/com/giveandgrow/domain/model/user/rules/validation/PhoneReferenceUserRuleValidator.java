package com.giveandgrow.domain.model.user.rules.validation;

import com.giveandgrow.domain.model.GenericValidationRule;
import com.giveandgrow.shared.validators.structure.GenericValidationDataStructure;
import org.springframework.stereotype.Service;

@Service
public class PhoneReferenceUserRuleValidator implements GenericValidationRule<String> {

    private GenericValidationDataStructure genericValidationDataStructure;

    public static final String FIELD_NAME_PHONE_REFERENCE = "phone reference ";

    @Override
    public void validate(String phoneReference) {

        genericValidationDataStructure.validateDataNotNullOrEmpty(phoneReference, FIELD_NAME_PHONE_REFERENCE);

        genericValidationDataStructure.validateLengthDataRange(phoneReference,8,10,FIELD_NAME_PHONE_REFERENCE);

        genericValidationDataStructure.validateFormatDataOnlyDigits(phoneReference,FIELD_NAME_PHONE_REFERENCE);

    }
}
