package com.ems.eventmanagementspring.entities.dtos;

import com.ems.eventmanagementspring.entities.enums.AttendeeRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttendeeDto {

    private Long idAttendee;
    private String fullName;
    private AttendeeRole attendeeRole;
    private List<AttendeeEventDto> attendeeEventDtoList;

}
