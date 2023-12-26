package com.ems.eventmanagementspring.entities.dtos;

import com.ems.eventmanagementspring.entities.enums.MemberEventStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttendeeEventDto {

    private Long idAttendeeEvent;
//    private AttendeeDto attendee;
    private EventDto eventDto;
    private MemberEventStatus status;
    private boolean isInvited;

}
