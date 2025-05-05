package com.giveandgrow.application.dto;

import java.time.LocalDate;
import java.util.UUID;
import com.giveandgrow.shared.helper.LongHelper;
import com.giveandgrow.shared.helper.TextHelper;
import com.giveandgrow.shared.helper.UuidHelper;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventDTO {

    private UUID id;

    private String name;

    private LocalDate startDateTime;

    private LocalDate registrationDeadline;

    private String location;

    private Integer maxParticipants;

    private Integer currentParticipantsCount;

    private UUID organizationId;

    private UUID userToRegister;


    public EventDTO() {
        setId(UuidHelper.DEFAULT_UUID);
        setName(TextHelper.EMPTY);
        setStartDateTime(LocalDate.now());
        setRegistrationDeadline(LocalDate.now());
        setLocation(TextHelper.EMPTY);
        setMaxParticipants(LongHelper.DEFAULT_LONG.intValue());
        setCurrentParticipantsCount(LongHelper.DEFAULT_LONG.intValue());
        setOrganizationId(UuidHelper.DEFAULT_UUID);
        setUserToRegister(UuidHelper.DEFAULT_UUID);

    }
}
