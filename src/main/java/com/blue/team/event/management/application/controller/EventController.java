package com.blue.team.event.management.application.controller;

import com.blue.team.event.management.application.model.dto.EventDto;
import com.blue.team.event.management.application.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventDto create(@RequestBody EventDto dto) {
        return service.create(dto);
    }

    @GetMapping("/{id}")
    public EventDto read(@PathVariable Long id){return service.read(id);}
}