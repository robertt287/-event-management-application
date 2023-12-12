package com.blue.team.event.management.application.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReadNotificationDto {

    private Long id;
    private String message;
    private LocalDateTime createdAt;
}
