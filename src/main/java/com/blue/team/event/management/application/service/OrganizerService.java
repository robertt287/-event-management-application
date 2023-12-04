package com.blue.team.event.management.application.service;

import com.blue.team.event.management.application.model.dto.OrganizerDto;
import com.blue.team.event.management.application.model.entity.OrganizerEntity;
import com.blue.team.event.management.application.repository.OrganizerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizerService {

    private final OrganizerRepository repository;

    public OrganizerDto create(OrganizerDto dto) {
        return entityToDto(repository.save(dtoToEntity(dto)));
    }

    private OrganizerEntity dtoToEntity(OrganizerDto dto) {
        return OrganizerEntity.builder().contactNumber(dto.getContactNumber()).email(dto.getEmail()).id(dto.getId()).fullName(dto.getFullName()).build();
    }

    private OrganizerDto entityToDto(OrganizerEntity entity) {
        return OrganizerDto.builder().fullName(entity.getFullName()).id(entity.getId()).email(entity.getEmail()).contactNumber(entity.getContactNumber()).build();
    }


}
