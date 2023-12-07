package com.blue.team.event.management.application.service;

import com.blue.team.event.management.application.model.ModelMapper;
import com.blue.team.event.management.application.model.dto.EventDto;
import com.blue.team.event.management.application.model.dto.Occurence;
import com.blue.team.event.management.application.model.dto.SortBy;
import com.blue.team.event.management.application.model.dto.SortDirection;
import com.blue.team.event.management.application.model.entity.EventEntity;
import com.blue.team.event.management.application.repository.EventRepository;
import com.blue.team.event.management.application.repository.EventSpecifications;
import com.blue.team.event.management.application.repository.OrganizerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository repository;
    private final ModelMapper modelMapper;
    private final OrganizerRepository organizerRepository;
    private final EventSpecifications specifications;

    public EventDto create(EventDto dto) {
        EventEntity entity = modelMapper.eventDtoToEntity(dto);
        entity.setOrganizer(organizerRepository.findById(dto.getOrganizer().getId()).orElseThrow(EntityNotFoundException::new));

        return modelMapper.eventEntityToDto(repository.save(entity));
    }

    public EventDto read(Long id) {
        return modelMapper.eventEntityToDto(repository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public List<EventDto> read(Occurence occurence, String nameKeyword, String location, SortBy sortBy, Sort.Direction sortDirection) {

        Specification<EventEntity> specification = Specification.where(null);

        if (occurence != null) {
            if (occurence.equals(Occurence.UPCOMING))
                specification = specification.and(specifications.isUpcoming());
            else if (occurence.equals(Occurence.PAST)) {
                specification = specification.and(specifications.isPast());
            }
        }

        if (nameKeyword != null && !nameKeyword.isBlank()){
            specification = specification.and(specifications.nameContains(nameKeyword));
        }

        if (location != null && !location.isBlank()){
            specification = specification.and(specifications.atLocation(location));
        }

        return modelMapper.eventEntitiesToDtos(repository.findAll(specification, Sort.by(sortDirection, sortBy.getField())));
    }
}