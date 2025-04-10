package com.giveandgrow.domain.model.user.rules.validation;

import com.giveandgrow.domain.model.ValidationRule;
import com.giveandgrow.shared.validators.structure.GenericValidationDataStructure;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailUserRuleValidator implements ValidationRule<String> {

	public static final String FIELD_NAME_EMAIL = "Email ";
	private final GenericValidationDataStructure genericValidationDataStructure;


    @Override
	public void validate(String email) {

		genericValidationDataStructure.validateDataNotNullOrEmpty(email,FIELD_NAME_EMAIL);

		genericValidationDataStructure.validateLengthDataRange(email,11,40,FIELD_NAME_EMAIL);

		genericValidationDataStructure.validateFormatDataOnlyLettersAndDigitsAtDomain(email,FIELD_NAME_EMAIL);
	}
}
