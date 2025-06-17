package com.giveandgrow.domain.model.user.rules.validation;

import com.giveandgrow.domain.model.GenericValidationRule;
import com.giveandgrow.shared.validators.structure.GenericValidationDataStructure;
import org.springframework.stereotype.Service;

@Service
public class InstitutionUserRuleValidator implements GenericValidationRule<String> {

    public static final String FIELD_NAME_INSTITUTION = "institution ";

    private GenericValidationDataStructure genericValidationDataStructure;

    @Override
    public void validate(String institution) {

        genericValidationDataStructure.validateDataNotNullOrEmpty(institution, FIELD_NAME_INSTITUTION);

        genericValidationDataStructure.validateLengthDataRange(institution,3,50,FIELD_NAME_INSTITUTION);

        genericValidationDataStructure.validateFormatText(institution,FIELD_NAME_INSTITUTION);

    }
}
