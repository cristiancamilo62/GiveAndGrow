package com.giveandgrow.shared.validators.exceptions;

import com.giveandgrow.shared.exception.custom.SharedGiveAndGrowException;

public class InvalidFormatDataRuleValidatorException extends SharedGiveAndGrowException {

    public InvalidFormatDataRuleValidatorException(String message) {
        super(message);
    }
}
