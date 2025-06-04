package com.giveandgrow.application.dto;

import com.giveandgrow.shared.helper.TextHelper;
import com.giveandgrow.shared.helper.UuidHelper;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UserDTO {
    
    private UUID id;  
    
    private String identification;

    private String firstName;
    
    private String middleName;
    
    private String lastName;
    
    private String middleLastName;
    
    private String email;

    private String institution;

    private String phoneOfNumber;

    private String phoneNumber;

    private String password;

    
    public UserDTO() {
        setId(UuidHelper.DEFAULT_UUID);
        setIdentification(TextHelper.EMPTY);
        setFirstName(TextHelper.EMPTY);
        setMiddleName(TextHelper.EMPTY);
        setLastName(TextHelper.EMPTY);
        setMiddleLastName(TextHelper.EMPTY);
        setEmail(TextHelper.EMPTY);
        setInstitution(TextHelper.EMPTY);
        setPhoneOfNumber(TextHelper.EMPTY);
        setPhoneNumber(TextHelper.EMPTY);
        setPassword(TextHelper.EMPTY);
    }
}

