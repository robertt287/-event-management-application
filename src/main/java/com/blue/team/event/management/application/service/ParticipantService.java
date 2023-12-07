package com.blue.team.event.management.application.service;

import com.blue.team.event.management.application.exception.MaxParticipantsReachedException;
import com.blue.team.event.management.application.model.ModelMapper;
import com.blue.team.event.management.application.model.dto.ParticipantDto;
import com.blue.team.event.management.application.model.entity.EventEntity;
import com.blue.team.event.management.application.model.entity.ParticipantEntity;
import com.blue.team.event.management.application.repository.EventRepository;
import com.blue.team.event.management.application.repository.ParticipantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class ParticipantService {
    private final ParticipantRepository repository;
    private final ModelMapper modelMapper;
    private final EventRepository eventRepository;

    public ParticipantDto create(ParticipantDto dto) {
        ParticipantEntity entity = modelMapper.participantDtoToEntity(dto);
        EventEntity eventEntity = eventRepository.findById(dto.getEventId()).orElseThrow(EntityNotFoundException::new);

        if (eventEntity.getParticipants().size() >= eventEntity.getMaximumParticipants()) {
            throw new MaxParticipantsReachedException();
        }
        entity.setEvent(eventEntity);

        return modelMapper.participantEntityToDto(repository.save(entity));

    }
}
