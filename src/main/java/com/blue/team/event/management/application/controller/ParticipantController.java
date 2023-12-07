package com.blue.team.event.management.application.controller;

import com.blue.team.event.management.application.model.dto.ParticipantDto;
import com.blue.team.event.management.application.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/participant")
@RequiredArgsConstructor
public class ParticipantController {

    private final ParticipantService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipantDto create(@RequestBody ParticipantDto dto) {
            return service.create(dto);
    }

}
