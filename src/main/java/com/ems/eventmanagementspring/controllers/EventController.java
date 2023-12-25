package com.ems.eventmanagementspring.controllers;

import com.ems.eventmanagementspring.entities.dtos.AddEventDto;
import com.ems.eventmanagementspring.entities.dtos.EventDto;
import com.ems.eventmanagementspring.services.interfaces.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public EventDto findById(@PathVariable Long id) {
        return eventService.findById(id);
    }

    @PostMapping
    public EventDto save(@RequestBody AddEventDto addEventDto) {
        return eventService.save(addEventDto);
    }

}
