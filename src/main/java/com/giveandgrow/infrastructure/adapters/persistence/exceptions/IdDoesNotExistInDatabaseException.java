package com.giveandgrow.infrastructure.adapters.persistence.exceptions;

import com.giveandgrow.shared.exception.custom.InfrastructureGiveAndGrowException;

public class IdDoesNotExistInDatabaseException extends InfrastructureGiveAndGrowException {

    public IdDoesNotExistInDatabaseException(String technicalMessage, String userMessage) {
        super(technicalMessage, userMessage);

    }
    
}
