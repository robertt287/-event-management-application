package com.blue.team.event.management.application.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class NotificationDto {

    private Long id;
    @NotNull
    private Long eventId;
    @NotNull
    private String message;
    @NotNull
    private LocalDateTime createdAt;
}
