package com.giveandgrow.domain.model.user.exceptions;

import com.giveandgrow.shared.exception.custom.ServiceProfileUserException;
import com.giveandgrow.shared.messages.MessageCatalog;
import com.giveandgrow.shared.messages.enums.MessageCode;

public class UserEmailAlreadyExistException extends ServiceProfileUserException {

	public UserEmailAlreadyExistException() {
		super(MessageCatalog.getContentMessage(MessageCode.M0000012));
	}

}
