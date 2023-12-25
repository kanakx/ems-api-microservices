package com.ems.eventmanagementspring.repositories;

import com.ems.eventmanagementspring.entities.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {



}
