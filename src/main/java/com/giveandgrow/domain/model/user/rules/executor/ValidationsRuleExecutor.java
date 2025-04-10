package com.giveandgrow.domain.model.user.rules.executor;

import com.giveandgrow.domain.model.ValidationRule;
import com.giveandgrow.domain.model.user.UserDomain;
import com.giveandgrow.domain.model.user.rules.business.UserEmailAlreadyExistBusinessRule;
import com.giveandgrow.domain.model.user.rules.business.UserIdentificationAlreadyExistBusinessRule;
import com.giveandgrow.domain.model.user.rules.validation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidationsRuleExecutor implements ValidationRule<UserDomain> {

    private final IdUserRuleValidator idUserRuleValidator;

    private final NameUserRuleValidator nameUserRuleValidator;

    private final IdentificationUserRuleValidator identificationUserRuleValidator;

    private final EmailUserRuleValidator emailUserRuleValidator;

    private final PasswordUserRuleValidator passwordUserRuleValidator;

    private final PhoneNumberUserRuleValidator phoneNumberUserRuleValidator;

   private final UserIdentificationAlreadyExistBusinessRule userIdentificationAlreadyExistBusinessRule;

   private final UserEmailAlreadyExistBusinessRule userEmailAlreadyExistBusinessRule;



    @Override
    public void validate(UserDomain domain) {

        idUserRuleValidator.validate(domain.getId());

        identificationUserRuleValidator.validate(domain.getIdentification());

        nameUserRuleValidator.validate(domain);

        emailUserRuleValidator.validate(domain.getEmail());

        phoneNumberUserRuleValidator.validate(domain.getPhoneNumber());

        passwordUserRuleValidator.validate(domain.getPassword());

        userIdentificationAlreadyExistBusinessRule.validate(domain.getIdentification());

        userEmailAlreadyExistBusinessRule.validate(domain.getEmail());
    }
}
