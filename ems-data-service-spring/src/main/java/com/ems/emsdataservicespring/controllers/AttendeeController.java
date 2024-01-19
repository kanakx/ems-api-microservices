package com.ems.emsdataservicespring.controllers;

import com.ems.emsdataservicespring.entities.dtos.AttendeeDto;
import com.ems.emsdataservicespring.services.interfaces.AttendeeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/data/attendees")
@RequiredArgsConstructor
public class AttendeeController {

    private static final Logger logger = LoggerFactory.getLogger(AttendeeController.class);
    private final AttendeeService attendeeService;

    @GetMapping("/{id}")
    public AttendeeDto findById(@PathVariable Long id) {
        logger.info("Received request to find attendee by ID: {}", id);
        AttendeeDto attendeeDto = attendeeService.findById(id);
        logger.info("Attendee retrieval completed for ID: {}", id);
        return attendeeDto;
    }

}
