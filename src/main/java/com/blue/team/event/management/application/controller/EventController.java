package com.blue.team.event.management.application.controller;

import com.blue.team.event.management.application.model.dto.EventDto;
import com.blue.team.event.management.application.model.dto.Occurence;
import com.blue.team.event.management.application.model.dto.SortBy;
import com.blue.team.event.management.application.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public EventDto read(@PathVariable Long id) {
        return service.read(id);
    }

    @GetMapping
    public List<EventDto> read(@RequestParam(required = false) Occurence occurence,
                               @RequestParam (required = false)String nameKeyword,
                               @RequestParam(required = false) String location,
                               @RequestParam (required = false, defaultValue = "DATE")SortBy sortBy,
                               @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sortDirection) {
        return service.read(occurence, nameKeyword, location, sortBy, sortDirection);
    }
}