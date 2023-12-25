package com.ems.eventmanagementspring.services.implementations;

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

}
