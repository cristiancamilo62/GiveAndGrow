package com.giveandgrow.domain.model.user.exceptions;

import com.giveandgrow.shared.exception.custom.DomainGiveAndGrowException;
import com.giveandgrow.shared.messages.MessageCatalog;
import com.giveandgrow.shared.messages.enums.MessageCode;

public class UserIdAlreadyExistException extends DomainGiveAndGrowException {

	public UserIdAlreadyExistException() {
		super(MessageCatalog.getContentMessage(MessageCode.M0000014),MessageCatalog.getContentMessage(MessageCode.M0000003));
	}

}
