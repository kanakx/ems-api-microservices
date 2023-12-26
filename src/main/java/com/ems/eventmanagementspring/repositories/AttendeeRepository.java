package com.ems.eventmanagementspring.repositories;

import com.ems.eventmanagementspring.entities.models.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendeeRepository extends JpaRepository<Attendee, Long> {



}
