package com.ems.emsdataservicespring.entities.dtos;

import com.ems.emsdataservicespring.entities.enums.MemberEventStatus;
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
    private EventDto eventDto;
    private MemberEventStatus status;

}
