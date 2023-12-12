package com.blue.team.event.management.application.controller;

import com.blue.team.event.management.application.model.dto.WriteParticipantDto;
import com.blue.team.event.management.application.service.ParticipantService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/participant")
@RequiredArgsConstructor
public class ParticipantController {

    private final ParticipantService service;

    @Transactional
    @PostMapping
    public ResponseEntity<Void> create(HttpServletRequest request, @RequestBody WriteParticipantDto dto) {
        Long id = service.create(dto);
        return ResponseEntity.created(URI.create(request.getRequestURI() + "/" + id)).build();
    }

}
