package com.giveandgrow.application.dto;

import com.giveandgrow.shared.helper.TextHelper;
import com.giveandgrow.shared.helper.UuidHelper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
public class PostulationDTO {

    private UUID id;

    private UUID userId;

    private UUID eventId;

    private String status;

    private String createAt;


    public PostulationDTO() {
        setId(UuidHelper.DEFAULT_UUID);
        setUserId(UuidHelper.DEFAULT_UUID);
        setEventId(UuidHelper.DEFAULT_UUID);
        setStatus(TextHelper.EMPTY);
        setCreateAt(TextHelper.EMPTY);
    }
}
