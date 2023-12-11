package com.blue.team.event.management.application.service;

import com.blue.team.event.management.application.model.ModelMapper;
import com.blue.team.event.management.application.model.dto.NotificationDto;
import com.blue.team.event.management.application.model.entity.EventEntity;
import com.blue.team.event.management.application.model.entity.NotificationEntity;
import com.blue.team.event.management.application.repository.EventRepository;
import com.blue.team.event.management.application.repository.NotificationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository repository;
    private final ModelMapper modelMapper;
    private final EventRepository eventRepository;

    public NotificationDto create(NotificationDto dto) {
        NotificationEntity entity = modelMapper.notificationDtoToEntity(dto);
        entity.setCreatedAt(LocalDateTime.now());

        EventEntity eventEntity = eventRepository.findById(dto.getEventId()).orElseThrow(EntityNotFoundException::new);
        entity.setEvent(eventEntity);

        log.info("Notification with id {} was saved.", entity.getId());

        return modelMapper.notificationEntityToDto(repository.save(entity));
    }
}
