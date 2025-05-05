package com.giveandgrow.domain.model.organization.exceptions;

import com.giveandgrow.shared.exception.custom.DomainGiveAndGrowException;
import com.giveandgrow.shared.messages.MessageCatalog;
import com.giveandgrow.shared.messages.enums.MessageCode;

public class OrganizationEmailAlreadyExistException extends DomainGiveAndGrowException {

    public OrganizationEmailAlreadyExistException() {
        super(MessageCatalog.getContentMessage(MessageCode.M0000019));
    }
}
