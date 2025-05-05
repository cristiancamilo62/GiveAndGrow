	package com.giveandgrow.domain.model.user.rules.validation;

    import com.giveandgrow.domain.model.GenericValidationRule;
	import com.giveandgrow.domain.model.user.UserDomain;
	import com.giveandgrow.shared.validators.structure.GenericValidationDataStructure;
	import org.springframework.stereotype.Service;

	@Service
	public class NameUserRuleValidator implements GenericValidationRule<UserDomain> {

		public static final String FIELD_NAME_FIRST_NAME= "first name ";
		public static final String FIELD_NAME_MIDDLE_NAME = "middle name ";
		public static final String FIELD_NAME_LAST_NAME= "last name ";
		public static final String FIELD_NAME_MIDDLE_LAST_NAME = "middle last name ";
		private final GenericValidationDataStructure genericValidationDataStructure;

        public NameUserRuleValidator(GenericValidationDataStructure genericValidationDataStructure) {
            this.genericValidationDataStructure = genericValidationDataStructure;
        }

        @Override
		public void validate(UserDomain patientDomain) {
			validateFirstName(patientDomain.getFirstName());
			validateMiddleName(patientDomain.getMiddleName());
			validateLastName(patientDomain.getLastName());
			validateMiddleLastName(patientDomain.getMiddleLastName());
		}


		private void validateFirstName(String firstname) {

			genericValidationDataStructure.validateDataNotNullOrEmpty(firstname, FIELD_NAME_FIRST_NAME);

			genericValidationDataStructure.validateLengthDataRange(firstname,4,20, FIELD_NAME_FIRST_NAME);

			genericValidationDataStructure.validateFormatDataOnlyLettersAndDigits(firstname, FIELD_NAME_FIRST_NAME);
		}


		private void validateMiddleName(String middleName) {

			genericValidationDataStructure.validateLengthDataOptional(middleName,20,FIELD_NAME_MIDDLE_NAME);

			genericValidationDataStructure.validateFormatDataOptional(middleName,FIELD_NAME_MIDDLE_NAME);
		}

		private void validateLastName(String lastName) {

			genericValidationDataStructure.validateDataNotNullOrEmpty(lastName, FIELD_NAME_LAST_NAME);

			genericValidationDataStructure.validateLengthDataRange(lastName,4,20, FIELD_NAME_LAST_NAME);

			genericValidationDataStructure.validateFormatDataOnlyLettersAndDigits(lastName, FIELD_NAME_LAST_NAME);
		}

		private void validateMiddleLastName(String middleLastName) {

			genericValidationDataStructure.validateLengthDataOptional(middleLastName,20,FIELD_NAME_MIDDLE_LAST_NAME);

			genericValidationDataStructure.validateFormatDataOptional(middleLastName,FIELD_NAME_MIDDLE_LAST_NAME);
		}



	}
