package com.blue.team.event.management.application.model.dto;

import lombok.Builder;
import lombok.Data;
@Data
@Builder

public class ParticipantDto {

    private Long id;
    private String fullName;
    private String email;
    private String contactNumber;
    private Long eventId;

}
