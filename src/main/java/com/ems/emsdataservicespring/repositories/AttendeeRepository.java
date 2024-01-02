package com.ems.emsdataservicespring.repositories;

import com.ems.emsdataservicespring.entities.models.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, Long> {



}
