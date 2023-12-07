package com.blue.team.event.management.application.repository;

import com.blue.team.event.management.application.model.entity.EventEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class EventSpecifications {

    public Specification<EventEntity> isUpcoming() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("date"), LocalDate.now());
    }

    public Specification<EventEntity> isPast() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(root.get("date"), LocalDate.now());
    }

    public Specification<EventEntity> nameContains(String keyword) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + keyword + "%");
    }

    public Specification<EventEntity> atLocation(String location) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("location"), location);
    }


}
