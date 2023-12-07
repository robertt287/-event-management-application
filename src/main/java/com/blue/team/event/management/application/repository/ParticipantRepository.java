package com.blue.team.event.management.application.repository;

import com.blue.team.event.management.application.model.entity.ParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository

public interface ParticipantRepository extends JpaRepository <ParticipantEntity, Long> {

}
