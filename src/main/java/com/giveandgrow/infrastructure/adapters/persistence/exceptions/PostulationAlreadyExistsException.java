package com.giveandgrow.infrastructure.adapters.persistence.exceptions;

import com.giveandgrow.shared.exception.custom.InfrastructureGiveAndGrowException;

public class PostulationAlreadyExistsException extends InfrastructureGiveAndGrowException {


    public PostulationAlreadyExistsException(String technicalMessage, String userMessage) {
        super(technicalMessage, userMessage);
    }
}
