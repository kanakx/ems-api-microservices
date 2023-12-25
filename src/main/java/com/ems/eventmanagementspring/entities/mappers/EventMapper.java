package com.ems.eventmanagementspring.entities.mappers;

import com.ems.eventmanagementspring.entities.dtos.EventDto;
import com.ems.eventmanagementspring.entities.models.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventDto mapToDto(Event event);

}
