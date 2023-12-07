package com.blue.team.event.management.application.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDto {

    private String message;
}
