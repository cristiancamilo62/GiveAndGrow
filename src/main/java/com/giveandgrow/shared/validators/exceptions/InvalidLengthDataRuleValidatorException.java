package com.giveandgrow.shared.validators.exceptions;

import com.giveandgrow.shared.exception.custom.SharedProfileUserException;

public class InvalidLengthDataRuleValidatorException extends SharedProfileUserException {

    public InvalidLengthDataRuleValidatorException(String userMessage) {
        super(userMessage);
    }
}
