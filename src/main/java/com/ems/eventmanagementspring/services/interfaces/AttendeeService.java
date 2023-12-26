package com.ems.eventmanagementspring.services.interfaces;

import com.ems.eventmanagementspring.entities.dtos.AttendeeDto;

public interface AttendeeService {

    AttendeeDto findById(Long id);

}
