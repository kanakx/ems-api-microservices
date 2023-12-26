package com.ems.eventmanagementspring.entities.mappers;

import com.ems.eventmanagementspring.entities.dtos.AddEventDto;
import com.ems.eventmanagementspring.entities.dtos.EventDto;
import com.ems.eventmanagementspring.entities.models.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventDto mapToDto(Event event);

    @Mapping(target = "idEvent", ignore = true)
    Event mapToEntity(AddEventDto addEventDto);

}
