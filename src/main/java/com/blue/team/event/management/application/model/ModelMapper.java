package com.blue.team.event.management.application.model;

import com.blue.team.event.management.application.model.dto.*;
import com.blue.team.event.management.application.model.entity.EventEntity;
import com.blue.team.event.management.application.model.entity.NotificationEntity;
import com.blue.team.event.management.application.model.entity.OrganizerEntity;
import com.blue.team.event.management.application.model.entity.ParticipantEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ModelMapper {

    public ReadEventDto eventEntityToReadDto(EventEntity entity) {
        return ReadEventDto.builder()
                .date(entity.getDate())
                .time(entity.getTime())
                .name(entity.getName())
                .id(entity.getId())
                .description(entity.getDescription())
                .location(entity.getLocation())
                .maximumParticipants(entity.getMaximumParticipants())
                .organizer(organizerEntityToReadDto(entity.getOrganizer()))
                .participants(participantEntitiesToReadDtos(entity.getParticipants()))
                .notifications(notificationEntitiesToDtos(entity.getNotifications()))
                .build();
    }

    private List<ReadNotificationDto> notificationEntitiesToDtos(List<NotificationEntity> entities) {
        if (entities == null)
            return Collections.emptyList();
        return entities.stream().map(this::notificationEntityToReadDto).toList();
    }

    public EventEntity writeEventDtoToEntity(WriteEventDto dto) {
        return EventEntity.builder()
                .date(dto.getDate())
                .time(dto.getTime())
                .name(dto.getName())
                .description(dto.getDescription())
                .location(dto.getLocation())
                .maximumParticipants(dto.getMaximumParticipants())
                .build();
    }

    public List<ReadEventDto> eventEntitiesToReadDtos(List<EventEntity> entities) {
        if (entities == null)
            return Collections.emptyList();
        return entities.stream()
                .map(this::eventEntityToReadDto)
                .toList();
    }

    public OrganizerEntity writeOrganizerDtoToEntity(WriteOrganizerDto dto) {
        return OrganizerEntity.builder().contactNumber(dto.getContactNumber()).email(dto.getEmail()).fullName(dto.getFullName()).build();
    }

    public ReadOrganizerDto organizerEntityToReadDto(OrganizerEntity entity) {
        return ReadOrganizerDto.builder().fullName(entity.getFullName()).id(entity.getId()).email(entity.getEmail()).contactNumber(entity.getContactNumber()).build();
    }

    public ParticipantEntity writeParticipantDtoToEntity(WriteParticipantDto dto) {
        return ParticipantEntity.builder().contactNumber(dto.getContactNumber()).email(dto.getEmail()).fullName(dto.getFullName()).build();
    }

    public ReadParticipantDto participantEntityToReadDto(ParticipantEntity entity) {
        return ReadParticipantDto.builder().contactNumber(entity.getContactNumber()).email(entity.getEmail()).id(entity.getId()).fullName(entity.getFullName()).build();

    }

    public List<ReadParticipantDto> participantEntitiesToReadDtos(List<ParticipantEntity> entities) {
        if (entities == null)
            return Collections.emptyList();
        return entities.stream()
                .map(this::participantEntityToReadDto)
                .toList();
    }

    public NotificationEntity writeNotificationDtoToEntity(WriteNotificationDto dto) {
        return NotificationEntity.builder().message(dto.getMessage()).build();
    }

    public ReadNotificationDto notificationEntityToReadDto(NotificationEntity entity) {
        return ReadNotificationDto.builder().id(entity.getId()).id(entity.getEvent().getId()).message(entity.getMessage()).createdAt(entity.getCreatedAt()).build();
    }
}
