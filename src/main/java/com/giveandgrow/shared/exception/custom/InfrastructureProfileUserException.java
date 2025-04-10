package com.giveandgrow.shared.exception.custom;


import com.giveandgrow.shared.exception.ProfileUserException;
import com.giveandgrow.shared.exception.enums.LocationException;
import java.io.Serial;

public class InfrastructureProfileUserException extends ProfileUserException {

	@Serial
	private static final long serialVersionUID = 1L;
	private static final LocationException location = LocationException.DTO;

	public InfrastructureProfileUserException(final String userMessage) {
		super(userMessage, location);
	}

	public InfrastructureProfileUserException(final String technicalMessage, final String userMessage) {
		super(technicalMessage, userMessage, location);
	}

	public InfrastructureProfileUserException(final String technicalMessage, final String userMessage,
											  final Throwable rootException) {
		super(technicalMessage, userMessage, location, rootException);
	}
}
