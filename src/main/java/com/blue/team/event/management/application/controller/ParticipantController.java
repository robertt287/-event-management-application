package com.blue.team.event.management.application.controller;

import com.blue.team.event.management.application.exception.MaxParticipantsException;
import com.blue.team.event.management.application.model.dto.ParticipantDto;
import com.blue.team.event.management.application.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/participant")
@RequiredArgsConstructor

public class ParticipantController {
    private final ParticipantService service;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody ParticipantDto dto) {

        try {
            return ResponseEntity.ok(service.create(dto));
        } catch (MaxParticipantsException e) {
          return  ResponseEntity.status(400).body(e.getMessage());
        }

    }
}
