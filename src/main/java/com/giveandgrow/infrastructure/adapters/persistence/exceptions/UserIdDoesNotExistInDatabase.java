package com.giveandgrow.infrastructure.adapters.persistence.exceptions;

import com.giveandgrow.shared.exception.custom.InfrastructureProfileUserException;

public class UserIdDoesNotExistInDatabase extends InfrastructureProfileUserException {


    public UserIdDoesNotExistInDatabase(String technicalMessage, String userMessage) {
        super(technicalMessage, userMessage);
    }
}
