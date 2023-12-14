package com.blue.team.event.management.application.unit_test;

import com.blue.team.event.management.application.model.ModelMapper;
import com.blue.team.event.management.application.model.dto.WriteEventDto;
import com.blue.team.event.management.application.model.dto.WriteOrganizerDto;
import com.blue.team.event.management.application.model.entity.EventEntity;
import com.blue.team.event.management.application.model.entity.OrganizerEntity;
import com.blue.team.event.management.application.repository.EventRepository;
import com.blue.team.event.management.application.repository.OrganizerRepository;
import com.blue.team.event.management.application.service.EventService;
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

    @Test
    void testCreate() {
//        given
        Long id = 1L;
        String location = "Acolo";
        LocalDate date = LocalDate.of(2023, 12, 18);

        WriteOrganizerDto organizerDto = WriteOrganizerDto.builder().fullName("Ana Banana")
                .email("banana@yahoo.com")
                .contactNumber("+40722541620")
                .build();

        OrganizerEntity savedOrganizerEntity = OrganizerEntity.builder().id(id)
                .fullName("Ana Banana")
                .email("banana@yahoo.com")
                .contactNumber("+40722541620")
                .build();

        WriteEventDto dto = WriteEventDto.builder().name("Event")
                .location(location)
                .date(date)
                .time(LocalTime.of(12, 30))
                .description("An interesting event.")
                .maximumParticipants(100)
                .organizerId(1L)
                .build();

        EventEntity eventEntity = EventEntity.builder().name("Event").
                location(location)
                .date(date)
                .time(LocalTime.of(12, 30))
                .description("An interesting event.")
                .maximumParticipants(100)
                .organizer(savedOrganizerEntity)
                .build();

        EventEntity savedEventEntity = EventEntity.builder().id(id)
                .name("Event").
                location(location)
                .date(LocalDate.now())
                .time(LocalTime.now())
                .description("An interesting event.")
                .maximumParticipants(100)
                .organizer(savedOrganizerEntity)
                .build();



        when(repository.save(eventEntity)).thenReturn(savedEventEntity);
        when(organizerRepository.findById(1L)).thenReturn(Optional.ofNullable(savedOrganizerEntity));

//        when
        Long createdId = service.create(dto);

//        then
        verify(repository, times(1)).save(eventEntity);
        assertEquals(id, createdId);
    }
}
