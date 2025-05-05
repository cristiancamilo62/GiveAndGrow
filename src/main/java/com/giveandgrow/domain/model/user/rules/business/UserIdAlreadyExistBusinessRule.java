package com.giveandgrow.domain.model.user.rules.business;

import com.giveandgrow.domain.model.user.exceptions.UserIdAlreadyExistException;
import com.giveandgrow.domain.model.GenericValidationRule;
import com.giveandgrow.domain.ports.output.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserIdAlreadyExistBusinessRule implements GenericValidationRule<UUID> {

	private final UserRepositoryPort userRepositoryPort;

	@Override
	public void validate(UUID id) {
		if(userRepositoryPort.findById(id).isPresent()) {
			throw new UserIdAlreadyExistException();
		}
	}
}
