package com.ems.emsdataservicespring.entities.mappers;

import com.ems.emsdataservicespring.entities.dtos.AttendeeEventDto;
import com.ems.emsdataservicespring.entities.models.AttendeeEvent;
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

    @Mapping(source = "event", target = "eventDto")
    AttendeeEventDto mapToDto(AttendeeEvent attendeeEvent);

}
