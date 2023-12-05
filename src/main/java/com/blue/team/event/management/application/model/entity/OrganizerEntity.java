package com.blue.team.event.management.application.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Data
@Builder
@Table(name = "organizer")
@NoArgsConstructor
@AllArgsConstructor
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

}

