package com.giveandgrow.domain.model.user.rules.validation;

import com.giveandgrow.domain.model.ValidationRule;
import com.giveandgrow.shared.validators.structure.GenericValidationDataStructure;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class IdUserRuleValidator implements ValidationRule<UUID> {

	public static final String FIELD_NAME_ID_PATIENT = "id Patient ";
	private final GenericValidationDataStructure genericValidationDataStructure;

    public IdUserRuleValidator(GenericValidationDataStructure genericValidationDataStructure) {
        this.genericValidationDataStructure = genericValidationDataStructure;
    }

    @Override
	public void validate(UUID idPatient) {

		genericValidationDataStructure.validateDataNotNullOrEmpty(idPatient,FIELD_NAME_ID_PATIENT);

		genericValidationDataStructure.validateUuidDefault(idPatient,FIELD_NAME_ID_PATIENT);
	}
}
