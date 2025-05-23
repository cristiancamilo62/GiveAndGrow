package com.giveandgrow.shared.exception.custom;


import com.giveandgrow.shared.exception.GiveAndGrowException;
import com.giveandgrow.shared.exception.enums.LocationException;
import java.io.Serial;

public class InfrastructureGiveAndGrowException extends GiveAndGrowException {

	@Serial
	private static final long serialVersionUID = 1L;
	private static final LocationException location = LocationException.DTO;

	public InfrastructureGiveAndGrowException(final String userMessage) {
		super(userMessage, location);
	}

	public InfrastructureGiveAndGrowException(final String technicalMessage, final String userMessage) {
		super(technicalMessage, userMessage, location);
	}

	public InfrastructureGiveAndGrowException(final String technicalMessage, final String userMessage,
											  final Throwable rootException) {
		super(technicalMessage, userMessage, location, rootException);
	}
}
