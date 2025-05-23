package com.giveandgrow.domain.model.organization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import com.giveandgrow.domain.model.event.EventDomain;
import com.giveandgrow.domain.model.security.Role;
import com.giveandgrow.shared.helper.ObjectHelper;
import com.giveandgrow.shared.helper.TextHelper;
import com.giveandgrow.shared.helper.UuidHelper;
import lombok.Getter;

@Getter
public class OrganizationDomain {

    private UUID id;

    private String name;

    private String description;

    private String contactNumber;

    private String email;

    private String password;

    private String address;

    private final Role role;

    private final List<EventDomain> events = new ArrayList<>();


    public OrganizationDomain(){
        setId(UuidHelper.DEFAULT_UUID);
        setName(TextHelper.EMPTY);
        setDescription(TextHelper.EMPTY);
        setContactNumber(TextHelper.EMPTY);
        setEmail(TextHelper.EMPTY);
        setPassword(TextHelper.EMPTY);
        setAddress(TextHelper.EMPTY);
        this.role = Role.ORG;
    }


    public OrganizationDomain(UUID id, String name, String description, String contactNumber,
            String email, String password, String address) {
        setId(id);
        setName(name);
        setDescription(description);
        setContactNumber(contactNumber);
        setEmail(email);
        setPassword(password);
        setAddress(address);
        this.role = Role.ORG;
    }

    public final void setId(UUID id) {
        this.id = id;
    }

    public final void setName(String name){
        this.name = ObjectHelper.getDefault(TextHelper.applyTrim(name), TextHelper.EMPTY);
    }

    public final void setDescription(String description){
        this.description = ObjectHelper.getDefault(TextHelper.applyTrim(description), TextHelper.EMPTY);
    }

    public final void setContactNumber(String contactNumber){
        this.contactNumber = ObjectHelper.getDefault(TextHelper.applyTrim(contactNumber), TextHelper.EMPTY);
    }

    public final void setEmail(String email){
        this.email = ObjectHelper.getDefault(TextHelper.applyTrim(email), TextHelper.EMPTY);
    }

    public final void setPassword(String password){
        this.password = ObjectHelper.getDefault(TextHelper.applyTrim(password), TextHelper.EMPTY);
    }

    public final void setAddress(String address){
        this.address = ObjectHelper.getDefault(TextHelper.applyTrim(address), TextHelper.EMPTY);
    }

    public List<EventDomain> getEvents() {
        return Collections.unmodifiableList(events);
    }
    public void addEvent(EventDomain eventDomain) {
        events.add(eventDomain);
    }
    public void removeEvent(EventDomain eventDomain) { events.remove(eventDomain); }

}
