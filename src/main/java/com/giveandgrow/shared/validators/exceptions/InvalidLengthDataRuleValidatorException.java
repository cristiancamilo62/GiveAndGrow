package com.giveandgrow.shared.validators.exceptions;

import com.giveandgrow.shared.exception.custom.SharedGiveAndGrowException;

public class InvalidLengthDataRuleValidatorException extends SharedGiveAndGrowException {

    public InvalidLengthDataRuleValidatorException(String userMessage) {
        super(userMessage);
    }
}
