package com.blue.team.event.management.application.controller;

import com.blue.team.event.management.application.exception.MaxParticipantsReachedException;
import com.blue.team.event.management.application.model.dto.ErrorDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleEntityNotFoundException() {
        return ErrorDto.builder().message("Resource not found.").build();
    }

    @ExceptionHandler(MaxParticipantsReachedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleMaxParticipantsException(MaxParticipantsReachedException exception) {
        return ErrorDto.builder().message(exception.getMessage()).build();
    }

}
