package com.ems.emsdataservicespring.services.interfaces;

import com.ems.emsdataservicespring.entities.dtos.AddEventDto;
import com.ems.emsdataservicespring.entities.dtos.EventDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {

    List<EventDto> findAll();
    EventDto findById(Long id);
    EventDto save(AddEventDto addEventDto);
    EventDto update(Long id, EventDto updatedEventDto);
    void deleteById(Long id);

}
