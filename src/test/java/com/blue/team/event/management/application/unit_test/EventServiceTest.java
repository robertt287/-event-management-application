package com.blue.team.event.management.application.unit_test;

import com.blue.team.event.management.application.model.ModelMapper;
import com.blue.team.event.management.application.model.dto.WriteEventDto;
import com.blue.team.event.management.application.model.entity.EventEntity;
import com.blue.team.event.management.application.model.entity.OrganizerEntity;
import com.blue.team.event.management.application.repository.EventRepository;
import com.blue.team.event.management.application.repository.OrganizerRepository;
import com.blue.team.event.management.application.service.EventService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @Mock
    private EventRepository repository;

    @Mock
    private OrganizerRepository organizerRepository;
    @InjectMocks
    private EventService service;

    @Spy
    private ModelMapper modelMapper;

    private static WriteEventDto getWriteEventDto(String eventName, String eventLocation, LocalDate date, LocalTime time, String eventDescription, int maximumParticipants, Long organizerId) {
        WriteEventDto dto = WriteEventDto.builder().name(eventName)
                .location(eventLocation)
                .date(date)
                .time(time)
                .description(eventDescription)
                .maximumParticipants(maximumParticipants)
                .organizerId(organizerId)
                .build();
        return dto;
    }

    @Test
    void testCreate_ShouldPass_WithSetOrganizer() {
//        given
        String location = "Acolo";
        String name = "Event";
        String eventDescription = "An interesting event.";
        String email = "banana@yahoo.com";
        LocalDate date = LocalDate.of(2023, 12, 18);
        LocalTime time = LocalTime.of(12, 30);
        int maximumParticipants = 100;
        Long id = 1L;
        Long organizerId = 1L;
        String organizerName = "Ana Banana";
        String OrganizerContactNumber = "+40722541620";

        OrganizerEntity savedOrganizerEntity = OrganizerEntity.builder().id(id)
                .fullName(organizerName)
                .email(email)
                .contactNumber(OrganizerContactNumber)
                .build();

        WriteEventDto dto = getWriteEventDto(name, location, date, time, eventDescription, maximumParticipants, organizerId);

        EventEntity eventEntity = EventEntity.builder().name(name)
                .location(location)
                .date(date)
                .time(time)
                .description(eventDescription)
                .maximumParticipants(maximumParticipants)
                .organizer(savedOrganizerEntity)
                .build();

        EventEntity savedEventEntity = EventEntity.builder().id(id)
                .name(name).
                location(location)
                .date(LocalDate.now())
                .time(LocalTime.now())
                .description(eventDescription)
                .maximumParticipants(maximumParticipants)
                .organizer(savedOrganizerEntity)
                .build();

        when(repository.save(eventEntity)).thenReturn(savedEventEntity);
        when(organizerRepository.findById(organizerId)).thenReturn(Optional.ofNullable(savedOrganizerEntity));

//        when
        Long createdId = service.create(dto);

//        then
        verify(repository, times(1)).save(eventEntity);
        assertEquals(id, createdId);
    }

    @Test
    void testCreate_ShouldFail_WithoutSetOrganizer() {
//        given
        String eventLocation = "Acolo";
        String eventName = "Event";
        String eventDescription = "An interesting event.";
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        int maximumParticipants = 100;
        Long organizerId = 1L;

        WriteEventDto dto = getWriteEventDto(eventName, eventLocation, date, time, eventDescription, maximumParticipants, organizerId);

        when(organizerRepository.findById(organizerId)).thenThrow(new EntityNotFoundException());

//        when
//        then
        assertThrows(EntityNotFoundException.class, () -> service.create(dto));

    }
}
