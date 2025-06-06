package com.giveandgrow.domain.model.organization.rules.executor;

import com.giveandgrow.domain.model.organization.rules.business.OrganizationContactNumberAlreadyExistBusinessRule;
import com.giveandgrow.domain.model.organization.rules.business.OrganizationEmailAlreadyExistBusinessRule;
import org.springframework.stereotype.Service;
import com.giveandgrow.domain.model.GenericValidationRule;
import com.giveandgrow.domain.model.organization.OrganizationDomain;
import com.giveandgrow.domain.model.organization.rules.validation.AddressOrganizationRuleValidator;
import com.giveandgrow.domain.model.organization.rules.validation.ContactNumberOrganizationRuleValidator;
import com.giveandgrow.domain.model.organization.rules.validation.DescriptionOrganizationRuleValidator;
import com.giveandgrow.domain.model.organization.rules.validation.EmailOrganizationRuleValidator;
import com.giveandgrow.domain.model.organization.rules.validation.IdOrganizationRuleValidator;
import com.giveandgrow.domain.model.organization.rules.validation.NameOrganizationRuleValidator;
import com.giveandgrow.domain.model.organization.rules.validation.PasswordOrganizationRuleValidator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrganizationValidationsRuleExecutor implements GenericValidationRule<OrganizationDomain> {

    private final IdOrganizationRuleValidator idOrganizationRuleValidator;
    private final DescriptionOrganizationRuleValidator descriptionOrganizationRuleValidator;
    private final NameOrganizationRuleValidator nameOrganizationRuleValidator;
    private final EmailOrganizationRuleValidator emailOrganizationRuleValidator;
    private final PasswordOrganizationRuleValidator passwordOrganizationRuleValidator;
    private final ContactNumberOrganizationRuleValidator contactNumberOrganizationRuleValidator;
    private final AddressOrganizationRuleValidator addressOrganizationRuleValidator;
    private final OrganizationEmailAlreadyExistBusinessRule organizationEmailAlreadyExistBusinessRule;
    private final OrganizationContactNumberAlreadyExistBusinessRule organizationContactNumberAlreadyExistBusinessRule;

    @Override
    public void validate(OrganizationDomain organizationDomain) {
        idOrganizationRuleValidator.validate(organizationDomain.getId());
        nameOrganizationRuleValidator.validate(organizationDomain.getName());
        descriptionOrganizationRuleValidator.validate(organizationDomain.getDescription());
        emailOrganizationRuleValidator.validate(organizationDomain.getEmail());
        passwordOrganizationRuleValidator.validate(organizationDomain.getPassword());
        contactNumberOrganizationRuleValidator.validate(organizationDomain.getContactNumber());
        addressOrganizationRuleValidator.validate(organizationDomain.getAddress());
        organizationEmailAlreadyExistBusinessRule.validate(organizationDomain);
        organizationContactNumberAlreadyExistBusinessRule.validate(organizationDomain);
      
    }

    
}
