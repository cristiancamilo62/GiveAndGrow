package com.giveandgrow.infrastructure.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
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

    @Column(name = "address")
    private String address;

    @Column(name= "category")
    private String category;

    @Column(name= "max_participants")
    private Integer maxParticipants;

    @Column(name = "description")
    private String description;

    @Column(name= "current_participants_count")
    private Integer currentParticipantsCount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "organization_id", nullable = false)
    private OrganizationEntity organization;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostulationEntity> postulations = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name = "event_user",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private final List<UserEntity> users = new ArrayList<>();

}
