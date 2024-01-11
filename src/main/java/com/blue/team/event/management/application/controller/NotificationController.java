package com.blue.team.event.management.application.controller;

import com.blue.team.event.management.application.model.dto.WriteNotificationDto;
import com.blue.team.event.management.application.service.NotificationService;
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
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService service;

    @Transactional
    @PostMapping
    public ResponseEntity<Void> create(HttpServletRequest request, @RequestBody WriteNotificationDto dto) {
        Long id = service.create(dto);
        return ResponseEntity.created(URI.create(request.getRequestURI() + "/" + id)).build();
    }
}
