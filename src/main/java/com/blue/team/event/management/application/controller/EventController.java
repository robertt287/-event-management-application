package com.blue.team.event.management.application.controller;

import com.blue.team.event.management.application.model.dto.ReadEventDto;
import com.blue.team.event.management.application.model.dto.WriteEventDto;
import com.blue.team.event.management.application.model.dto.enums.Occurence;
import com.blue.team.event.management.application.model.dto.enums.SortBy;
import com.blue.team.event.management.application.service.EventService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService service;

    @Transactional
    @PostMapping
    public ResponseEntity<Void> create(HttpServletRequest request, @RequestBody WriteEventDto dto) {
        Long id = service.create(dto);
        return ResponseEntity.created(URI.create(request.getRequestURI() + "/" + id)).build();
    }

    @GetMapping("/{id}")
    public ReadEventDto read(@PathVariable Long id) {
        return service.read(id);
    }

    @GetMapping
    public List<ReadEventDto> read(@RequestParam(required = false) Occurence occurence,
                                   @RequestParam(required = false) String nameKeyword,
                                   @RequestParam(required = false) String location,
                                   @RequestParam(required = false, defaultValue = "DATE") SortBy sortBy,
                                   @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sortDirection) {
        return service.read(occurence, nameKeyword, location, sortBy, sortDirection);
    }

    @Transactional
    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody WriteEventDto dto) {
        service.update(id, dto);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
}