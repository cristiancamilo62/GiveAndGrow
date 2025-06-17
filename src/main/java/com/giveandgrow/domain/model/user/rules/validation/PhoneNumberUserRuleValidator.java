package com.giveandgrow.domain.model.user.rules.validation;

import com.giveandgrow.domain.model.GenericValidationRule;
import com.giveandgrow.shared.validators.structure.GenericValidationDataStructure;
import org.springframework.stereotype.Service;

@Service
public class PhoneNumberUserRuleValidator implements GenericValidationRule<String> {

	public static final String FIELD_NAME_PHONE_NUMBER = "phone number ";
	private final GenericValidationDataStructure genericValidationDataStructure;

    public PhoneNumberUserRuleValidator(GenericValidationDataStructure genericValidationDataStructure) {
        this.genericValidationDataStructure = genericValidationDataStructure;
    }

    @Override
	public void validate(String phoneNumber) {

		genericValidationDataStructure.validateLengthDataOptional(phoneNumber,10,FIELD_NAME_PHONE_NUMBER);
		genericValidationDataStructure.validateFormatDataOptional(phoneNumber,FIELD_NAME_PHONE_NUMBER);
	}
}
