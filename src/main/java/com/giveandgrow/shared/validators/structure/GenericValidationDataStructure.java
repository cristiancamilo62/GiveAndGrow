package com.giveandgrow.shared.validators.structure;

import com.giveandgrow.shared.helper.ObjectHelper;
import com.giveandgrow.shared.helper.TextHelper;
import com.giveandgrow.shared.helper.UuidHelper;
import com.giveandgrow.shared.messages.MessageCatalog;
import com.giveandgrow.shared.messages.enums.MessageCode;
import com.giveandgrow.shared.validators.exceptions.DefaultDataRuleValidatorException;
import com.giveandgrow.shared.validators.exceptions.EmptyOrNullDataRuleValidatorException;
import com.giveandgrow.shared.validators.exceptions.InvalidFormatDataRuleValidatorException;
import com.giveandgrow.shared.validators.exceptions.InvalidLengthDataRuleValidatorException;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public final class GenericValidationDataStructure {

    private GenericValidationDataStructure() {
        super();
    }

    public void validateDataNotNullOrEmpty(Object value, String dataName){
        //TODO : organizar
        if(ObjectHelper.isNull(value) || ObjectHelper.isEmpty(value)){
            String userMessage = dataName + MessageCatalog.getContentMessage(MessageCode.M0000004);
            throw new EmptyOrNullDataRuleValidatorException(userMessage);
        }
    }

    public void validateFormatDataOnlyDigits(String value, String dataName){
        if(!TextHelper.containsOnlyDigits(value)){
            String userMessage = dataName + MessageCatalog.getContentMessage(MessageCode.M0000005);
            throw new InvalidFormatDataRuleValidatorException(userMessage);
        }
    }

    public void validateFormatDataDateTime(String value, String dataName) {
        if (!TextHelper.isValidDateTime(value)) {
            String userMessage = dataName + MessageCatalog.getContentMessage(MessageCode.M0000007);
            throw new InvalidFormatDataRuleValidatorException(userMessage);
        }
    }

    public void validateFormatDataOnlyLettersAndDigits(String value, String dataName) {
        if(!TextHelper.containsOnlyLettersDigits(value)){
            String userMessage = dataName + MessageCatalog.getContentMessage(MessageCode.M0000006);
            throw new InvalidFormatDataRuleValidatorException(userMessage);
        }
    }

    public void validateFormatDataOnlyLettersAndDigitsAtSpace(String value, String dataName) {
        if(!TextHelper.containsOnlyLettersDigitsSpaces(value)){
            String userMessage = dataName + MessageCatalog.getContentMessage(MessageCode.M0000020);
            throw new InvalidFormatDataRuleValidatorException(userMessage);
        }
    }

    public void validateFormatText(String value, String dataName) {
        if(!TextHelper.isValidText(value)){
            String userMessage = dataName + MessageCatalog.getContentMessage(MessageCode.M0000022);
            throw new InvalidFormatDataRuleValidatorException(userMessage);
        }
    }

    public void validateFormatDataOnlyLettersAndDigitsAtDomain(String value, String dataName) {
        if(!TextHelper.containsOnlyLettersDigitsAtDomain(value)){
            String userMessage = dataName + MessageCatalog.getContentMessage(MessageCode.M0000008);
            throw new InvalidFormatDataRuleValidatorException(userMessage);
        }
    }

    public void validateFormatPassword(String value, String dataName) {
        if(!TextHelper.isValidPassword(value)){
            String userMessage = dataName + MessageCatalog.getContentMessage(MessageCode.M0000009);
            throw new InvalidFormatDataRuleValidatorException(userMessage);
        }
    }

    public void validateFormatAddress(String value, String dataName) {
        if(!TextHelper.isValidAddress(value)){
            String userMessage = dataName + MessageCatalog.getContentMessage(MessageCode.M0000021);
            throw new InvalidFormatDataRuleValidatorException(userMessage);
        }
    }
    public void validateFormatDataOptional(String value, String dataName) {

        if(!ObjectHelper.isEmpty(value)) {
            switch (TextHelper.applyTrim(dataName)) {
                case "phone number ":
                    validateFormatDataOnlyDigits(value, dataName);
                    break;
                case "middle name", "middle last name":
                    validateFormatDataOnlyLettersAndDigits(value, dataName);
                    break;
            }
        }
    }
    public void validateLengthDataRange(String value, int minLength, int maxLength, String dataName) {
        if(!TextHelper.isValidLength(value, minLength, maxLength)){
            String userMessage = dataName + MessageCatalog.getContentMessage(MessageCode.M0000010) +  minLength + " and " + maxLength;
            throw new InvalidLengthDataRuleValidatorException(userMessage);
        }
    }
    public void validateLengthDataExact(String value, int length, String dataName) {
        if(!TextHelper.isExactLength(value, length)){
            String userMessage = dataName + MessageCatalog.getContentMessage(MessageCode.M0000011) + length ;
            throw new InvalidLengthDataRuleValidatorException(userMessage);
        }
    }

    public void validateLengthDataOptional(String value,int length, String dataName) {

        if(!ObjectHelper.isEmpty(value)) {
            switch (TextHelper.applyTrim(dataName)) {
                case "phone number":
                    validateLengthDataExact(value, length, dataName);
                    break;
                case "middle name", "middle last name":
                    validateLengthDataRange(value, 0, length, dataName);
                    break;
            }
        }
    }

    public void validateUuidDefault(UUID value, String dataName) {
        if(UuidHelper.isDefault(value)){
            String userMessage = dataName + MessageCatalog.getContentMessage(MessageCode.M0000012);
            throw new DefaultDataRuleValidatorException(userMessage);
        }
    }
}
