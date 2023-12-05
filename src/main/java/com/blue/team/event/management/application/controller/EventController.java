package com.blue.team.event.management.application.controller;

import com.blue.team.event.management.application.model.dto.EventDto;
import com.blue.team.event.management.application.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor

public class EventController {

    private final EventService service;

    @PostMapping
    public EventDto create(@RequestBody EventDto dto) {
        return service.create(dto);
    }
}