package com.blue.team.event.management.application.service;

import com.blue.team.event.management.application.model.ModelMapper;
import com.blue.team.event.management.application.model.dto.ReadEventDto;
import com.blue.team.event.management.application.model.dto.WriteEventDto;
import com.blue.team.event.management.application.model.dto.enums.Occurence;
import com.blue.team.event.management.application.model.dto.enums.SortBy;
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

    public Long create(WriteEventDto dto) {
        EventEntity entity = modelMapper.writeEventDtoToEntity(dto);
        entity.setOrganizer(organizerRepository.findById(dto.getOrganizerId()).orElseThrow(EntityNotFoundException::new));

        return modelMapper.eventEntityToReadDto(repository.save(entity)).getId();
    }

    public ReadEventDto read(Long id) {
        return modelMapper.eventEntityToReadDto(repository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public List<ReadEventDto> read(Occurence occurence, String nameKeyword, String location, SortBy sortBy, Sort.Direction sortDirection) {
        Specification<EventEntity> specification = Specification.where(null);

        if (occurence != null) {
            if (occurence.equals(Occurence.UPCOMING))
                specification = specification.and(specifications.isUpcoming());
            else if (occurence.equals(Occurence.PAST)) {
                specification = specification.and(specifications.isPast());
            }
        }

        if (nameKeyword != null && !nameKeyword.isBlank()) {
            specification = specification.and(specifications.nameContains(nameKeyword));
        }

        if (location != null && !location.isBlank()) {
            specification = specification.and(specifications.atLocation(location));
        }

        return modelMapper.eventEntitiesToReadDtos(repository.findAll(specification, Sort.by(sortDirection, sortBy.getField())));
    }

    public void update(Long id, WriteEventDto dto) {
        EventEntity entity = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setLocation(dto.getLocation());
        entity.setMaximumParticipants(dto.getMaximumParticipants());
        entity.setDate(dto.getDate());
        entity.setTime(dto.getTime());

        repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}