package com.giveandgrow.domain.model.user.exceptions;

import com.giveandgrow.shared.exception.custom.DomainGiveAndGrowException;
import com.giveandgrow.shared.messages.MessageCatalog;
import com.giveandgrow.shared.messages.enums.MessageCode;

public class UserIdentificationAlreadyExistException extends DomainGiveAndGrowException {

	public UserIdentificationAlreadyExistException() {

		super(MessageCatalog.getContentMessage(MessageCode.M0000015));
	}

}
