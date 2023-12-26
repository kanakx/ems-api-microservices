package com.ems.eventmanagementspring.entities.dtos;

import com.ems.eventmanagementspring.entities.enums.MemberEventStatus;

public class AttendeeEventDto {

    private Long idAttendeeEvent;
//    private AttendeeDto attendee;
    private EventDto event;
    private MemberEventStatus status;
    private boolean isInvited;

}
