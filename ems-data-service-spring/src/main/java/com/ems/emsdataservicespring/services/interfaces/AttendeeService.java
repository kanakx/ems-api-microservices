package com.ems.emsdataservicespring.services.interfaces;

import com.ems.emsdataservicespring.entities.dtos.AttendeeDto;

public interface AttendeeService {

    AttendeeDto findById(Long id);

}
