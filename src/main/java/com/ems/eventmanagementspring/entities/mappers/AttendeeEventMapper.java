package com.ems.eventmanagementspring.entities.mappers;

import com.ems.eventmanagementspring.entities.dtos.AttendeeEventDto;
import com.ems.eventmanagementspring.entities.models.AttendeeEvent;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {
                EventMapper.class
        },
        builder = @Builder(disableBuilder = true)
)
public interface AttendeeEventMapper {

//    @Mapping(target = "attendee", ignore = true)
    @Mapping(source = "event", target = "eventDto")
    AttendeeEventDto mapToDto(AttendeeEvent attendeeEvent);

}
