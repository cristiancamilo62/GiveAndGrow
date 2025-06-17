package com.giveandgrow.domain.model.user.rules.executor;

import com.giveandgrow.domain.model.GenericValidationRule;
import com.giveandgrow.domain.model.user.UserDomain;
import com.giveandgrow.domain.model.user.rules.business.UserEmailAlreadyExistBusinessRule;
import com.giveandgrow.domain.model.user.rules.business.UserIdentificationAlreadyExistBusinessRule;
import com.giveandgrow.domain.model.user.rules.validation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserValidationsRuleExecutor implements GenericValidationRule<UserDomain> {

    private final IdUserRuleValidator idUserRuleValidator;

    private final NameUserRuleValidator nameUserRuleValidator;

    private final IdentificationUserRuleValidator identificationUserRuleValidator;

    private final EmailUserRuleValidator emailUserRuleValidator;

    private final PasswordUserRuleValidator passwordUserRuleValidator;

    private final PhoneNumberUserRuleValidator phoneNumberUserRuleValidator;

    private final PhoneReferenceUserRuleValidator phoneReferenceUserRuleValidator;

    private final InstitutionUserRuleValidator institutionUserRuleValidator;

   private final UserIdentificationAlreadyExistBusinessRule userIdentificationAlreadyExistBusinessRule;

   private final UserEmailAlreadyExistBusinessRule userEmailAlreadyExistBusinessRule;



    @Override
    public void validate(UserDomain domain) {

        idUserRuleValidator.validate(domain.getId());

        identificationUserRuleValidator.validate(domain.getIdentification());

        nameUserRuleValidator.validate(domain);

        emailUserRuleValidator.validate(domain.getEmail());

        phoneNumberUserRuleValidator.validate(domain.getPhoneNumber());

        phoneReferenceUserRuleValidator.validate(domain.getPhoneOfReference());

        institutionUserRuleValidator.validate(domain.getInstitution());

        passwordUserRuleValidator.validate(domain.getPassword());

        userIdentificationAlreadyExistBusinessRule.validate(domain);

        userEmailAlreadyExistBusinessRule.validate(domain);
    }
}
