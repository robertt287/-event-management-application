package com.blue.team.event.management.application.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WriteNotificationDto {
    @NotNull
    private Long eventId;
    private String message;
}
