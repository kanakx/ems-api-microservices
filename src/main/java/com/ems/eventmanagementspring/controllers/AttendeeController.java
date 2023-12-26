package com.ems.eventmanagementspring.controllers;

import com.ems.eventmanagementspring.entities.dtos.AttendeeDto;
import com.ems.eventmanagementspring.services.interfaces.AttendeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/attendees")
@RequiredArgsConstructor
public class AttendeeController {

    private final AttendeeService attendeeService;

    @GetMapping("/{id}")
    public AttendeeDto findById(@PathVariable Long id) {
        return attendeeService.findById(id);
    }

}
