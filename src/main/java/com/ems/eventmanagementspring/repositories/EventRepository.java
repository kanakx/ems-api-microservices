package com.ems.eventmanagementspring.repositories;

import com.ems.eventmanagementspring.entities.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByIsPublic(Boolean isPublic);
    Optional<Event> findByNameAndStartTimestampAndLocationName(String name, LocalDateTime startTimestamp, String locationName);

}
