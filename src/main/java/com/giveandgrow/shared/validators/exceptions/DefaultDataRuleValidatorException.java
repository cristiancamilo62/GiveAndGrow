package com.giveandgrow.shared.validators.exceptions;

import com.giveandgrow.shared.exception.custom.SharedProfileUserException;

public class DefaultDataRuleValidatorException extends SharedProfileUserException {

    public DefaultDataRuleValidatorException(String userMessage) {
        super(userMessage);
    }
}
