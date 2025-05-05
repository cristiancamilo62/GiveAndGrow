package com.giveandgrow.domain.model.user.rules.validation;

import com.giveandgrow.domain.model.GenericValidationRule;
import com.giveandgrow.shared.validators.structure.GenericValidationDataStructure;
import org.springframework.stereotype.Service;

@Service
public class PasswordUserRuleValidator implements GenericValidationRule<String> {

	public static final String FIELD_NAME_PASSWORD = "password ";
	private final GenericValidationDataStructure genericValidationDataStructure;

    public PasswordUserRuleValidator(GenericValidationDataStructure genericValidationDataStructure) {
        this.genericValidationDataStructure = genericValidationDataStructure;
    }

    @Override
	public void validate(String password) {

		genericValidationDataStructure.validateDataNotNullOrEmpty(password, FIELD_NAME_PASSWORD);

		genericValidationDataStructure.validateLengthDataRange(password,8,60,FIELD_NAME_PASSWORD);

		genericValidationDataStructure.validateFormatPassword(password, FIELD_NAME_PASSWORD);
	}
}
