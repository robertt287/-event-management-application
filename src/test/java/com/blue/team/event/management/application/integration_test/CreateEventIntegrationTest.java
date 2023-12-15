package com.blue.team.event.management.application.integration_test;

import com.blue.team.event.management.application.model.ModelMapper;
import com.blue.team.event.management.application.model.dto.WriteEventDto;
import com.blue.team.event.management.application.service.SmsSender;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class CreateEventIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ObjectMapper objectMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @MockBean
    private SmsSender smsSender;

    @Test
    @Sql(scripts = {"classpath:create_organizer.sql"})
    void testCreateEventShouldPass() throws Exception {
        // given
        String location = "Acolo";
        String name = "Event";
        String description = "An interesting event.";
        LocalDate date = LocalDate.of(2023, 12, 18);
        LocalTime time = LocalTime.of(12, 30, 0);
        int maximumParticipants = 100;
        Long id = 1L;
        Long organizerId = 1L;

        WriteEventDto writeEventDto = WriteEventDto.builder().name(name)
                .location(location)
                .date(date)
                .time(time)
                .description(description)
                .maximumParticipants(maximumParticipants)
                .organizerId(organizerId)
                .build();

        //when
        //then
        mockMvc.perform(post("/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(writeEventDto)))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/event/" + id))
                .andReturn();

        assertEquals(1, entityManager.createNativeQuery(String.format("""
                SELECT id FROM event
                WHERE id = '%s'
                AND date = '%s'
                AND description = '%s'
                AND location = '%s'
                AND maximum_participants = '%s'
                AND name = '%s'
                AND time = '%s'
                AND organizer_id = '%s'""", id, date, description, location, maximumParticipants, name, time, organizerId)).getResultList().size());
    }

    @Test
    void testCreateEventShouldFail() throws Exception {
        //given
        String location = "Acolo";
        String name = "Event";
        String description = "An interesting event.";
        LocalDate date = LocalDate.of(2023, 12, 18);
        LocalTime time = LocalTime.of(12, 30, 0);
        int maximumParticipants = 100;
        Long id = 1L;
        Long organizerId = 1L;

        WriteEventDto writeEventDto = WriteEventDto.builder().name(name)
                .location(location)
                .date(date)
                .time(time)
                .description(description)
                .maximumParticipants(maximumParticipants)
                .organizerId(organizerId)
                .build();

        //when
        //then
        mockMvc.perform(post("/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(writeEventDto)))
                .andExpect(status().isNotFound())
                .andReturn();

        assertEquals(0, entityManager.createNativeQuery(String.format("""
                SELECT id FROM event
                WHERE id = '%s'
                """, id)).getResultList().size());
    }
}
