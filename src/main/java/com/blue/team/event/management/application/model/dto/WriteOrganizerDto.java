package com.blue.team.event.management.application.model.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WriteOrganizerDto {
    @NotNull(message = "Name must not be null.")
    @Size(min = 2, message = "Name must be at least 2 characters long.")
    private String fullName;
    @NotNull(message = "Email address must not be null.")
    @Pattern(regexp = ".+@.+\\..+", message = "Email address must be valid.")
    private String email;
    @NotNull(message = "Phone number must not be null.")
    @Pattern(regexp = "\\+?[0-9 ]*", message = "Phone number must be valid.")
    private String contactNumber;
}
