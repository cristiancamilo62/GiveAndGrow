package com.giveandgrow.shared.exception;

import com.giveandgrow.shared.exception.enums.LocationException;
import com.giveandgrow.shared.helper.ObjectHelper;
import com.giveandgrow.shared.helper.TextHelper;
import lombok.Getter;
import java.io.Serial;

@Getter
public class ProfileUserException extends RuntimeException{

	@Serial
	private static final long serialVersionUID = 1L;
	protected String userMessage;
	protected LocationException locationException;

	public ProfileUserException(LocationException locationException) {
		setLocationException(locationException);
	}

	public ProfileUserException(String technicalMessage, String userMessage, LocationException locationException, Throwable rootException) {
		super(technicalMessage, rootException);
		setMessageUser(userMessage);
		setLocationException(locationException);
	}

	public ProfileUserException(final String userMessage, final LocationException locationException) {
		super(userMessage);
		setMessageUser(userMessage);
		setLocationException(locationException);
	}

	public ProfileUserException(String technicalMessage, String userMessage, LocationException locationException) {
		super(technicalMessage);
		setMessageUser(userMessage);
		setLocationException(locationException);
	}

	private final void setMessageUser(final String userMessage) {
		this.userMessage = TextHelper.applyTrim(userMessage);
	}

	private final void setLocationException(final LocationException locationException) {
		this.locationException = ObjectHelper.getDefault(locationException, LocationException.GENERAL);
	}

}
