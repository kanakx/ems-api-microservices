package com.ems.eventmanagementspring.services.implementations;

import com.ems.eventmanagementspring.entities.dtos.AddEventDto;
import com.ems.eventmanagementspring.entities.dtos.EventDto;
import com.ems.eventmanagementspring.entities.mappers.EventMapper;
import com.ems.eventmanagementspring.entities.models.Event;
import com.ems.eventmanagementspring.exception.CustomApiException;
import com.ems.eventmanagementspring.repositories.EventRepository;
import com.ems.eventmanagementspring.services.interfaces.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventMapper eventMapper;
    private final EventRepository eventRepository;

    @Override
    public List<EventDto> findAll() {
        List<Event> eventList = eventRepository.findAll();

        return eventList.stream()
                .map(eventMapper::mapToDto)
                .toList();
    }

    @Override
    public EventDto findById(Long id) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        Event event = eventOptional
                .orElseThrow(() -> CustomApiException.builder()
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .message("Event not found")
                        .build());

        return eventMapper.mapToDto(event);
    }

    @Override
    public EventDto save(AddEventDto addEventDto) {
        Optional<Event> eventOptional = eventRepository.findByNameAndStartTimestampAndLocationName(addEventDto.getName(), addEventDto.getStartTimestamp(), addEventDto.getLocationName());

        eventOptional.ifPresent(event -> {
            throw CustomApiException.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message("Workout with such name, start time and location name already exists")
                    .build();
        });

        Event newEvent = eventMapper.mapToEntity(addEventDto);
        Event savedEvent = eventRepository.save(newEvent);

        return eventMapper.mapToDto(savedEvent);
    }

}
