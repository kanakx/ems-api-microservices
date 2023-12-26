package com.ems.eventmanagementspring.services.implementations;

import com.ems.eventmanagementspring.entities.dtos.AttendeeDto;
import com.ems.eventmanagementspring.entities.mappers.AttendeeMapper;
import com.ems.eventmanagementspring.entities.models.Attendee;
import com.ems.eventmanagementspring.exception.CustomApiException;
import com.ems.eventmanagementspring.repositories.AttendeeRepository;
import com.ems.eventmanagementspring.services.interfaces.AttendeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendeeServiceImpl implements AttendeeService {

    private final AttendeeMapper attendeeMapper;
    private final AttendeeRepository attendeeRepository;

    @Override
    public AttendeeDto findById(Long id) {
        Optional<Attendee> attendeeOptional = attendeeRepository.findById(id);
        Attendee attendee = attendeeOptional
                .orElseThrow(() -> CustomApiException.builder()
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .message("Attendee not found")
                        .build());

        return attendeeMapper.mapToDto(attendee);
    }

}
