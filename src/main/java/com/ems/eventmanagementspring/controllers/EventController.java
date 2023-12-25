package com.ems.eventmanagementspring.controllers;

import com.ems.eventmanagementspring.entities.dtos.EventDto;
import com.ems.eventmanagementspring.services.interfaces.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public List<EventDto> findAll() {
        return eventService.findAll();
    }

}
