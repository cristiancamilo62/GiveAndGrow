package com.giveandgrow.domain.model.user.rules.business;

import com.giveandgrow.domain.ports.output.UserRepositoryPort;
import com.giveandgrow.domain.model.user.exceptions.UserIdentificationAlreadyExistException;
import com.giveandgrow.domain.model.ValidationRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserIdentificationAlreadyExistBusinessRule implements ValidationRule<String> {
	
	private final UserRepositoryPort patientRepositoryPort;

	@Override
	public void validate(String identification) {

        if (patientRepositoryPort.findByIdentification(identification).isPresent()) {
            throw new UserIdentificationAlreadyExistException();
        }

    }
		

}
