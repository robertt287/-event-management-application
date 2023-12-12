package com.blue.team.event.management.application.controller;

import com.blue.team.event.management.application.model.dto.OrganizerDto;
import com.blue.team.event.management.application.service.OrganizerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/organizer")
@RequiredArgsConstructor
public class OrganizerController {

    private final OrganizerService service;

    @Transactional
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrganizerDto create(@RequestBody OrganizerDto dto) {
        return service.create(dto);
    }

}