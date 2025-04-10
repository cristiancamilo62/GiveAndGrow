package com.giveandgrow.shared.exception.custom;


import com.giveandgrow.shared.exception.ProfileUserException;
import com.giveandgrow.shared.exception.enums.LocationException;

import java.io.Serial;

public class DomainProfileUserException extends ProfileUserException {

	@Serial
	private static final long serialVersionUID = 1L;
	private static final LocationException location = LocationException.DOMAIN;
	
	  public DomainProfileUserException() {
	        super(location);
	    }

	public DomainProfileUserException(final String userMessage) {
		super(userMessage, location);
	}

	public DomainProfileUserException(final String technicalMessage, final String userMessage) {
		super(technicalMessage, userMessage, location);
	}

	public DomainProfileUserException(final String technicalMessage, final String userMessage,
									  final Throwable rootException) {
		super(technicalMessage, userMessage, location, rootException);
	}
}
