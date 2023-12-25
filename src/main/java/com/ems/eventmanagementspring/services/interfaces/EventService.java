package com.ems.eventmanagementspring.services.interfaces;

import com.ems.eventmanagementspring.entities.dtos.EventDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {

    List<EventDto> findAll();

}
