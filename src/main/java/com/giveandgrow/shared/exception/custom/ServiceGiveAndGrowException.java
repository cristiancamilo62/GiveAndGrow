package com.giveandgrow.shared.exception.custom;


import com.giveandgrow.shared.exception.GiveAndGrowException;
import com.giveandgrow.shared.exception.enums.LocationException;

import java.io.Serial;

public class ServiceGiveAndGrowException extends GiveAndGrowException {

	@Serial
	private static final long serialVersionUID = 1L;
	private static final LocationException location = LocationException.USECASE;
	
	public ServiceGiveAndGrowException(final String userMessage) {
		super(userMessage, location);
	}

	public ServiceGiveAndGrowException(final String technicalMessage, final String userMessage) {
		super(technicalMessage, userMessage, location);
	}

	public ServiceGiveAndGrowException(final String technicalMessage, final String userMessage,
									   final Throwable rootException) {
		super(technicalMessage, userMessage, location, rootException);
	}
}
