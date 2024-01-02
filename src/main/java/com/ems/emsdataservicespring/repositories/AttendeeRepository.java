package com.ems.emsdataservicespring.repositories;

import com.ems.emsdataservicespring.entities.models.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendeeRepository extends JpaRepository<Attendee, Long> {



}
