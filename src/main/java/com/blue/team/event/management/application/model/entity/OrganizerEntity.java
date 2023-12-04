package com.blue.team.event.management.application.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@Table(name = "organizer")
public class OrganizerEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String contactNumber;
    @OneToMany(mappedBy = "organizer")
    private List<EventEntity> events;
}

