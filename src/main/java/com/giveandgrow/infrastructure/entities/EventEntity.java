package com.giveandgrow.infrastructure.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "events")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventEntity {

    @Id
    @Column(name = "event_id")
    private UUID id;

    @Column(name= "event_name")
    private String name;

    @Column(name= "start_date_time")
    private LocalDateTime startDateTime;

    @Column(name= "registration_deadline")
    private LocalDateTime registrationDeadline;

    @Column(name = "location")  
    private String location;

    @Column(name= "category")
    private String category;

    @Column(name= "max_participants")
    private Integer maxParticipants;

    @Column(name= "current_participants_count")
    private Integer currentParticipantsCount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "organization_id", nullable = false)
    private OrganizationEntity organization;


    @ManyToMany
    @JoinTable(
            name = "event_user",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private final List<UserEntity> users = new ArrayList<>();

}
