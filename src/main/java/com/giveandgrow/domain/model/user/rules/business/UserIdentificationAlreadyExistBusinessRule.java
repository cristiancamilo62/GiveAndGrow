package com.giveandgrow.domain.model.user.rules.business;

import com.giveandgrow.domain.model.user.UserDomain;
import com.giveandgrow.domain.ports.output.UserRepositoryPort;
import com.giveandgrow.domain.model.user.exceptions.UserIdentificationAlreadyExistException;
import com.giveandgrow.domain.model.GenericValidationRule;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserIdentificationAlreadyExistBusinessRule implements GenericValidationRule<UserDomain> {
	
	private final UserRepositoryPort patientRepositoryPort;

	@Override
	public void validate(UserDomain userDomain) {

        if (patientRepositoryPort.existsByIdentification(userDomain.getIdentification(),userDomain.getId())) {
            throw new UserIdentificationAlreadyExistException();
        }

    }
		

}
