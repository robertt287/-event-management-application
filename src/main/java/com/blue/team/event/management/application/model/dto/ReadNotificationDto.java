package com.blue.team.event.management.application.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReadNotificationDto {
    @NotNull(message = "Id must not be null.")
    private Long id;
    @NotNull(message = "Message must not be null.")
    private String message;
    @NotNull(message = "Date and time of notification creation must not be null.")
    private LocalDateTime createdAt;
}
