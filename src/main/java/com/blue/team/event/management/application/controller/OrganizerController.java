package com.blue.team.event.management.application.controller;

import com.blue.team.event.management.application.model.dto.OrganizerDto;
import com.blue.team.event.management.application.service.OrganizerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organizer")
@RequiredArgsConstructor
public class OrganizerController {

    private final OrganizerService service;

    @PostMapping
    public OrganizerDto create(@RequestBody OrganizerDto dto) {
        return service.create(dto);
    }

}