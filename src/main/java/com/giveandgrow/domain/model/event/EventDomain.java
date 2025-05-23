package com.giveandgrow.domain.model.event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.giveandgrow.domain.model.user.UserDomain;
import com.giveandgrow.shared.helper.LongHelper;
import com.giveandgrow.shared.helper.ObjectHelper;
import com.giveandgrow.shared.helper.TextHelper;
import com.giveandgrow.shared.helper.UuidHelper;
import lombok.Getter;

@Getter
public class EventDomain {

    private UUID id;

    private String name;

    private LocalDateTime startDateTime;

    private LocalDateTime registrationDeadline;
 
    private String location;

    private String category;

    private Integer maxParticipants;

    private Integer currentParticipantsCount;

    private UUID organizationId ;

    private final List<UserDomain> users = new ArrayList<>();


    public EventDomain(){
        setId(UuidHelper.DEFAULT_UUID);
        setName(TextHelper.EMPTY);
        setStartDateTime(LocalDateTime.now());
        setRegisterDeadline(LocalDateTime.now());
        setLocation(TextHelper.EMPTY);
        setCategory(TextHelper.EMPTY);
        setMaxParticipants(LongHelper.DEFAULT_LONG.intValue());
        setCurrentParticipantsCount(LongHelper.DEFAULT_LONG.intValue());
        setOrganizationId(UuidHelper.DEFAULT_UUID);

    }

    public EventDomain(UUID id, String name, LocalDateTime startDateTime, LocalDateTime registerDeadline, String location, String category, UUID organizationId) {
        setId(id);
        setName(name);
        setStartDateTime(startDateTime);
        setRegisterDeadline(registerDeadline);
        setLocation(location);
        setCategory(category);
        setMaxParticipants(LongHelper.DEFAULT_LONG.intValue());
        setCurrentParticipantsCount(LongHelper.DEFAULT_LONG.intValue());
        setOrganizationId(organizationId);
        
    }

    public final void setId(UUID id) {
        this.id = id;
    }

    public final void setName(String name){
        this.name = ObjectHelper.getDefault(TextHelper.applyTrim(name), TextHelper.EMPTY);
    }

    public final void setStartDateTime(LocalDateTime startDateTime){
        this.startDateTime = ObjectHelper.getDefault(startDateTime, LocalDateTime.now());
    }

    public final void setRegisterDeadline(LocalDateTime registerDeadline){
        this.registrationDeadline = ObjectHelper.getDefault(registerDeadline, LocalDateTime.now());
    }

    public final void setLocation(String location){
        this.location = ObjectHelper.getDefault(TextHelper.applyTrim(location), TextHelper.EMPTY);
    }

    public final void setCategory(String category){
        this.category = ObjectHelper.getDefault(TextHelper.applyTrim(category), TextHelper.EMPTY);
    }

    public final void setOrganizationId(UUID organizationId){
        this.organizationId = ObjectHelper.getDefault(organizationId, UuidHelper.DEFAULT_UUID);
    }

    public final void setMaxParticipants(Integer maxParticipants){
        this.maxParticipants = ObjectHelper.getDefault(maxParticipants, LongHelper.DEFAULT_LONG.intValue());
    }

    public final void setCurrentParticipantsCount(Integer currentParticipantsCount){
        this.currentParticipantsCount =  ObjectHelper.getDefault(currentParticipantsCount, LongHelper.DEFAULT_LONG.intValue());
    }

    public void addUser(UserDomain user) {
        users.add(user);
    }

    public void removeUser(UserDomain user) {
        users.remove(user);
    }
}
    