package com.giveandgrow.domain.model.user.exceptions;

import com.giveandgrow.shared.exception.custom.DomainGiveAndGrowException;
import com.giveandgrow.shared.messages.MessageCatalog;
import com.giveandgrow.shared.messages.enums.MessageCode;

public class UserEmailAlreadyExistException extends DomainGiveAndGrowException {

	public UserEmailAlreadyExistException() {
		super(MessageCatalog.getContentMessage(MessageCode.M0000013));
	}
}
