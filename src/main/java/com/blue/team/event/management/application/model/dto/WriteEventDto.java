package com.blue.team.event.management.application.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class WriteEventDto {
    @NotNull(message = "Location must not be null.")
    private String location;
    @NotNull(message = "Name must not be null.")
    @Size(min = 2, message = "Name must be at least 2 characters long.")
    private String name;
    @NotNull(message = "Date must not be null.")
    private LocalDate date;
    @NotNull(message = "Time must not be null.")
    private LocalTime time;
    @Size(min = 5, message = "Description must be at least 5 characters long.")
    @NotNull(message = "Description must not be null.")
    private String description;
    @Min(value = 1, message = "The number of maximum participants must be greater than 1.")
    @NotNull(message = "The number of maximum participants must not be null.")
    private Integer maximumParticipants;
    @NotNull(message = "Organizer id must not be null.")
    private Long organizerId;
}
