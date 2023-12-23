package com.blue.team.event.management.application.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
public class ReadEventDto {
    @NotNull(message = "Id must not be null.")
    private Long id;
    @NotNull(message = "Location must not be null.")
    private String location;
    @NotNull(message = "Name must not be null.")
    private String name;
    @NotNull(message = "Date must not be null.")
    private LocalDate date;
    @NotNull(message = "Time must not be null.")
    private LocalTime time;
    @NotNull(message = "Description must not be null.")
    private String description;
    @Min(value = 1, message = "The number of maximum participants must be greater than 1.")
    @NotNull(message = "Name must not be null.")
    private Integer maximumParticipants;
    @NotNull(message = "Organizer must not be null.")
    private ReadOrganizerDto organizer;
    @Min(value = 1, message = "The participants list size must pe greater than 1.")
    @NotNull(message = "The participants list must not be null.")
    private List<ReadParticipantDto> participants;
    @Min(value = 1, message = "The notification list size must be greater than 1.")
    @NotNull(message = "The notification list size must not be null.")
    private List<ReadNotificationDto> notifications;
}
