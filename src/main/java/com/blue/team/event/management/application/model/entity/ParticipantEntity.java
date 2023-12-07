package com.blue.team.event.management.application.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@Table(name = "participant")
@NoArgsConstructor
@AllArgsConstructor

public class ParticipantEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String contactNumber;
    @ManyToOne (optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private EventEntity event;

}
