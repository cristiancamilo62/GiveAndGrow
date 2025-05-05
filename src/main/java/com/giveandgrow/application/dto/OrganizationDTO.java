package com.giveandgrow.application.dto;

import java.util.UUID;
import com.giveandgrow.shared.helper.TextHelper;
import com.giveandgrow.shared.helper.UuidHelper;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrganizationDTO {

    private UUID id;

    private String name;

    private String description;

    private String contactNumber;

    private String email;

    private String password;

    private String address;


    public OrganizationDTO() {
        setId(UuidHelper.DEFAULT_UUID);
        setName(TextHelper.EMPTY);
        setDescription(TextHelper.EMPTY);
        setContactNumber(TextHelper.EMPTY);
        setEmail(TextHelper.EMPTY);
        setPassword(TextHelper.EMPTY);
        setAddress(TextHelper.EMPTY);
    }

}
