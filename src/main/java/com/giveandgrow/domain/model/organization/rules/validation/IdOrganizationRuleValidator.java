package com.giveandgrow.domain.model.organization.rules.validation;

import java.util.UUID;
import org.springframework.stereotype.Service;
import com.giveandgrow.domain.model.GenericValidationRule;
import com.giveandgrow.shared.validators.structure.GenericValidationDataStructure;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class IdOrganizationRuleValidator implements GenericValidationRule<UUID> {

    public static final String FIELD_NAME_ID = "Id Organization ";
    private final GenericValidationDataStructure genericValidationDataStructure;

    @Override
    public void validate(UUID id) {

        genericValidationDataStructure.validateDataNotNullOrEmpty(id, FIELD_NAME_ID);

        genericValidationDataStructure.validateUuidDefault(id, FIELD_NAME_ID);
       
    }
}
