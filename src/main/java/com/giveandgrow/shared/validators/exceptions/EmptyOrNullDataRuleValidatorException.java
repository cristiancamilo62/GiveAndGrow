package com.giveandgrow.shared.validators.exceptions;

import com.giveandgrow.shared.exception.custom.SharedProfileUserException;

public class EmptyOrNullDataRuleValidatorException extends SharedProfileUserException {

    public EmptyOrNullDataRuleValidatorException(String message) {
        super(message);
    }
}
