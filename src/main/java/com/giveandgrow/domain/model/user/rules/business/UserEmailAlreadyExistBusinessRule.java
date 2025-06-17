package com.giveandgrow.domain.model.user.rules.business;

import com.giveandgrow.domain.model.user.UserDomain;
import com.giveandgrow.domain.ports.output.UserRepositoryPort;
import com.giveandgrow.domain.model.user.exceptions.UserEmailAlreadyExistException;
import com.giveandgrow.domain.model.GenericValidationRule;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserEmailAlreadyExistBusinessRule implements GenericValidationRule<UserDomain> {

	private final UserRepositoryPort patientRepositoryPort;

	@Override
    public void validate(UserDomain userDomain) {
        if (patientRepositoryPort.existsByEmail(userDomain.getEmail(),userDomain.getId())) {
            throw new UserEmailAlreadyExistException();
        }
    }
}

