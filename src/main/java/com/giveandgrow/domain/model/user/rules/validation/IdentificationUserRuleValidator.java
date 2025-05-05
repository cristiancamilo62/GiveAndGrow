package com.giveandgrow.domain.model.user.rules.validation;

import com.giveandgrow.domain.model.GenericValidationRule;
import com.giveandgrow.shared.validators.structure.GenericValidationDataStructure;
import org.springframework.stereotype.Service;

@Service
public class IdentificationUserRuleValidator implements GenericValidationRule<String> {

	public static final String FIELD_NAME_IDENTIFICATION = "Identification ";
	private final GenericValidationDataStructure genericValidationDataStructure;

    public IdentificationUserRuleValidator(GenericValidationDataStructure genericValidationDataStructure) {
        this.genericValidationDataStructure = genericValidationDataStructure;
    }

    @Override
	public void validate(String identification) {

		genericValidationDataStructure.validateDataNotNullOrEmpty(identification, FIELD_NAME_IDENTIFICATION);

		genericValidationDataStructure.validateLengthDataRange(identification,8,10,FIELD_NAME_IDENTIFICATION);

		genericValidationDataStructure.validateFormatDataOnlyDigits(identification,FIELD_NAME_IDENTIFICATION);
		
	}

}
