package com.giveandgrow.domain.model.organization.rules.validation;

import org.springframework.stereotype.Service;
import com.giveandgrow.domain.model.GenericValidationRule;
import com.giveandgrow.shared.validators.structure.GenericValidationDataStructure;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ContactNumberOrganizationRuleValidator implements GenericValidationRule<String> {

    public static final String FIELD_NAME_CONTACT_NUMBER = "Contact Number ";
    private final GenericValidationDataStructure genericValidationDataStructure;
    
    
    @Override
    public void validate(String contactNumber) {
        
        genericValidationDataStructure.validateDataNotNullOrEmpty(contactNumber,FIELD_NAME_CONTACT_NUMBER);

        genericValidationDataStructure.validateLengthDataRange(contactNumber,7,10,FIELD_NAME_CONTACT_NUMBER);

        genericValidationDataStructure.validateFormatDataOnlyDigits(contactNumber, FIELD_NAME_CONTACT_NUMBER);
    }

   
}
