package com.giveandgrow.shared.validators.exceptions;

import com.giveandgrow.shared.exception.custom.SharedProfileUserException;

public class InvalidFormatDataRuleValidatorException extends SharedProfileUserException {

    public InvalidFormatDataRuleValidatorException(String message) {
        super(message);
    }
}
