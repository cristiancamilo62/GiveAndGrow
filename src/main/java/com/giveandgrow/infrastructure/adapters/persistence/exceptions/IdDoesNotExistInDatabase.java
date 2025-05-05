package com.giveandgrow.infrastructure.adapters.persistence.exceptions;

import com.giveandgrow.shared.exception.custom.InfrastructureGiveAndGrowException;

public class IdDoesNotExistInDatabase extends InfrastructureGiveAndGrowException {

    public IdDoesNotExistInDatabase(String technicalMessage, String userMessage) {
        super(technicalMessage, userMessage);
    }
    
}
