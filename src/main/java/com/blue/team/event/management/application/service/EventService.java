package com.blue.team.event.management.application.service;

import com.blue.team.event.management.application.model.ModelMapper;
import com.blue.team.event.management.application.model.dto.EventDto;
import com.blue.team.event.management.application.model.entity.EventEntity;
import com.blue.team.event.management.application.repository.EventRepository;
import com.blue.team.event.management.application.repository.OrganizerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository repository;
    private final ModelMapper modelMapper;
    private final OrganizerRepository organizerRepository;

    public EventDto create(EventDto dto) {
        EventEntity entity = modelMapper.eventDtoToEntity(dto);
        entity.setOrganizer(organizerRepository.findById(dto.getOrganizer().getId()).orElseThrow(EntityNotFoundException::new));

        return modelMapper.eventEntityToDto(repository.save(entity));
    }


}