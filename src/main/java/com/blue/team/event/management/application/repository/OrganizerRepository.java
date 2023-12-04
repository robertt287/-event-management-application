package com.blue.team.event.management.application.repository;

import com.blue.team.event.management.application.model.entity.OrganizerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizerRepository extends JpaRepository<OrganizerEntity, Long> {
}
