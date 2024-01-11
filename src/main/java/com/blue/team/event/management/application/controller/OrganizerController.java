package com.blue.team.event.management.application.controller;

import com.blue.team.event.management.application.model.dto.WriteOrganizerDto;
import com.blue.team.event.management.application.service.OrganizerService;
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
@RequestMapping("/organizer")
@RequiredArgsConstructor
public class OrganizerController {

    private final OrganizerService service;

    @Transactional
    @PostMapping
    public ResponseEntity<Void> create(HttpServletRequest request, @RequestBody WriteOrganizerDto dto) {
        Long id = service.create(dto);
        return ResponseEntity.created(URI.create(request.getRequestURI() + "/" + id)).build();
    }

}