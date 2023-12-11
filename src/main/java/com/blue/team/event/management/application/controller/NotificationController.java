package com.blue.team.event.management.application.controller;

import com.blue.team.event.management.application.model.dto.NotificationDto;
import com.blue.team.event.management.application.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NotificationDto create(@RequestBody NotificationDto dto) {
        return service.create(dto);
    }
}
