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
    @NotNull
    private String location;
    @NotNull(message = "Name must not be null.")
    @Size(min = 2, message = "Name must be at least 2 characters long.")
    private String name;
    @NotNull
    private LocalDate date;
    @NotNull
    private LocalTime time;
    @NotNull
    private String description;
    @Min(1)
    @NotNull
    private Integer maximumParticipants;
    @NotNull
    private Long organizerId;
}
