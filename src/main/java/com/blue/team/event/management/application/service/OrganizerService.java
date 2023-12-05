package com.blue.team.event.management.application.service;

import com.blue.team.event.management.application.model.ModelMapper;
import com.blue.team.event.management.application.model.dto.OrganizerDto;
import com.blue.team.event.management.application.repository.OrganizerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizerService {

    private final OrganizerRepository repository;
    private final ModelMapper modelMapper;

    public OrganizerDto create(OrganizerDto dto) {
        return modelMapper.organizerEntityToDto(repository.save(modelMapper.organizerDtoToEntity(dto)));
    }


}
