package com.blue.team.event.management.application.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@Table(name = "notification")
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String message;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @ManyToOne(optional = false)
    @JoinColumn(name = "event_id")
    private EventEntity event;
}
