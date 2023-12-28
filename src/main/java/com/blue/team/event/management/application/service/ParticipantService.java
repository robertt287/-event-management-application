package com.blue.team.event.management.application.service;

import com.blue.team.event.management.application.exception.MaxParticipantsReachedException;
import com.blue.team.event.management.application.model.ModelMapper;
import com.blue.team.event.management.application.model.dto.WriteParticipantDto;
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
    private final EmailSender emailSender;

    public Long create(WriteParticipantDto dto) {
        ParticipantEntity entity = modelMapper.writeParticipantDtoToEntity(dto);
        EventEntity eventEntity = eventRepository.findById(dto.getEventId()).orElseThrow(EntityNotFoundException::new);

        if (eventEntity.getParticipants().size() >= eventEntity.getMaximumParticipants()) {
            throw new MaxParticipantsReachedException();
        }
        entity.setEvent(eventEntity);
        emailSender.registrationConfirmation(entity, eventEntity);

        return repository.save(entity).getId();

    }
}
