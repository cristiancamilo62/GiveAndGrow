package com.giveandgrow.domain.model.postulation;

import com.fasterxml.jackson.core.JsonToken;
import com.giveandgrow.domain.model.event.EventDomain;
import com.giveandgrow.domain.model.user.UserDomain;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@Data
public class PostulationDomain {

    private UUID id;

    private UUID userId;

    private UUID eventId;

    private PostulationStatus status;

    private String createAt;


    public enum PostulationStatus{
        PENDING,
        ACCEPTED,
        REJECTED

    }
}
