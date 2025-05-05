package com.giveandgrow.domain.model.user.rules.business;

import com.giveandgrow.domain.ports.output.UserRepositoryPort;
import com.giveandgrow.domain.model.user.exceptions.UserEmailAlreadyExistException;
import com.giveandgrow.domain.model.GenericValidationRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserEmailAlreadyExistBusinessRule implements GenericValidationRule<String> {

	private final UserRepositoryPort patientRepositoryPort;

	@Override
    public void validate(String email) {
        if (patientRepositoryPort.existsByEmail(email)) {
            throw new UserEmailAlreadyExistException();
        }
    }
}

