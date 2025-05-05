package com.giveandgrow.domain.model.organization.exceptions;

import com.giveandgrow.shared.exception.custom.DomainGiveAndGrowException;
import com.giveandgrow.shared.messages.MessageCatalog;
import com.giveandgrow.shared.messages.enums.MessageCode;

public class OrganizationContactNumberAlreadyExistException extends DomainGiveAndGrowException {

    public OrganizationContactNumberAlreadyExistException() {
        super(MessageCatalog.getContentMessage(MessageCode.M0000018));
    }

   
}
