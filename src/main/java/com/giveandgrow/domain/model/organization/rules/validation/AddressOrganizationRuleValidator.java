package com.giveandgrow.domain.model.organization.rules.validation;

import org.springframework.stereotype.Service;

import com.giveandgrow.domain.model.GenericValidationRule;
import com.giveandgrow.shared.validators.structure.GenericValidationDataStructure;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AddressOrganizationRuleValidator implements GenericValidationRule<String> {

    public static final String FIELD_NAME_ADDRESS = "Address Organization ";
    private final GenericValidationDataStructure genericValidationDataStructure;

    @Override
    public void validate(String address) {

        genericValidationDataStructure.validateDataNotNullOrEmpty(address, FIELD_NAME_ADDRESS);

        genericValidationDataStructure.validateLengthDataRange(address, 5, 40, FIELD_NAME_ADDRESS);

        genericValidationDataStructure.validateFormatAddress(address, FIELD_NAME_ADDRESS);
        
    }

}
