package com.giveandgrow.shared.validators.exceptions;

import com.giveandgrow.shared.exception.custom.SharedGiveAndGrowException;

public class EmptyOrNullDataRuleValidatorException extends SharedGiveAndGrowException {

    public EmptyOrNullDataRuleValidatorException(String message) {
        super(message);
    }
}
