package com.blue.team.event.management.application.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrganizerDto {

    private Long id;
    @NotNull
    private String fullName;
    @NotNull
    private String email;
    @NotNull
    private String contactNumber;

}
