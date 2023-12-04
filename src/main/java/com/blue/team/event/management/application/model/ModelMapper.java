package com.blue.team.event.management.application.model;

import com.blue.team.event.management.application.model.dto.EventDto;
import com.blue.team.event.management.application.model.dto.OrganizerDto;
import com.blue.team.event.management.application.model.entity.EventEntity;
import com.blue.team.event.management.application.model.entity.OrganizerEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ModelMapper {


    public EventDto eventEntityToDto(EventEntity entity) {
        return EventDto.builder()
                .date(entity.getDate())
                .time(entity.getTime())
                .name(entity.getName())
                .id(entity.getId())
                .description(entity.getDescription())
                .location(entity.getLocation())
                .maximumParticipants(entity.getMaximumParticipants())
                .organizer(organizerEntityToDto(entity.getOrganizer()))
                .build();
    }


    public EventEntity eventDtoToEntity(EventDto dto) {
        return EventEntity.builder()
                .date(dto.getDate())
                .time(dto.getTime())
                .name(dto.getName())
                .id(dto.getId())
                .description(dto.getDescription())
                .location(dto.getLocation())
                .maximumParticipants(dto.getMaximumParticipants())
                .organizer(organizerDtoToEntity(dto.getOrganizer()))
                .build();
    }

    public List<EventDto> eventEntitiesToDtos(List<EventEntity> entities) {
        if (entities == null)
            return Collections.emptyList();
        return entities.stream()
                .map(this::eventEntityToDto)
                .toList();
    }

    public OrganizerEntity organizerDtoToEntity(OrganizerDto dto) {
        return OrganizerEntity.builder().contactNumber(dto.getContactNumber()).email(dto.getEmail()).id(dto.getId()).fullName(dto.getFullName()).build();
    }

    public OrganizerDto organizerEntityToDto(OrganizerEntity entity) {
        return OrganizerDto.builder().events(eventEntitiesToDtos(entity.getEvents())).fullName(entity.getFullName()).id(entity.getId()).email(entity.getEmail()).contactNumber(entity.getContactNumber()).build();
    }
}
