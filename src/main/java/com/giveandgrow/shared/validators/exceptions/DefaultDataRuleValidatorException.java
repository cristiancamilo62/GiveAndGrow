package com.giveandgrow.shared.validators.exceptions;

import com.giveandgrow.shared.exception.custom.SharedGiveAndGrowException;

public class DefaultDataRuleValidatorException extends SharedGiveAndGrowException {

    public DefaultDataRuleValidatorException(String userMessage) {
        super(userMessage);
    }
}
