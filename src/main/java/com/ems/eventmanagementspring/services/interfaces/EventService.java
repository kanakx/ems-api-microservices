package com.ems.eventmanagementspring.services.interfaces;

import com.ems.eventmanagementspring.entities.dtos.AddEventDto;
import com.ems.eventmanagementspring.entities.dtos.EventDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {

    List<EventDto> findAll();
    EventDto findById(Long id);
    EventDto save(AddEventDto addEventDto);
    void deleteBydId(Long id);

}
