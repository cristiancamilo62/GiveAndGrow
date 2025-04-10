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

    private boolean confirmedEmail;

    private String phoneNumber;

    private boolean confirmedPhoneNumber;

    private String password;
    
    private String dateBirth;
    
    private boolean accountStatement;
    
    private String role;
    
    public UserDTO() {
        setId(UuidHelper.DEFAULT_UUID);
        setIdentification(TextHelper.EMPTY);
        setFirstName(TextHelper.EMPTY);
        setMiddleName(TextHelper.EMPTY);
        setLastName(TextHelper.EMPTY);
        setMiddleLastName(TextHelper.EMPTY);
        setEmail(TextHelper.EMPTY);
        setConfirmedEmail(false);
        setPhoneNumber(TextHelper.EMPTY);
        setConfirmedPhoneNumber(false);
        setPassword(TextHelper.EMPTY);
        setDateBirth(TextHelper.EMPTY);
        setAccountStatement(false);
        setRole(TextHelper.EMPTY);
    }
}

