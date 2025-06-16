package com.giveandgrow.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "postulations")
@Getter
@Setter
@Data
public class PostulationEntity {

    @Id
    @Column(name = "postulation_id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id",nullable = false)
    private EventEntity event;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private PostulationStatus status;

    @Column(name = "create_at",nullable = false)
    private String createAt;


    public enum PostulationStatus{
        PENDING,
        ACCEPTED,
        REJECTED
    }
}
