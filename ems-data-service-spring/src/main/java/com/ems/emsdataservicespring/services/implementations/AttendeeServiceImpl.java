package com.ems.emsdataservicespring.services.implementations;

import com.ems.emsdataservicespring.entities.dtos.AttendeeDto;
import com.ems.emsdataservicespring.entities.mappers.AttendeeMapper;
import com.ems.emsdataservicespring.entities.models.Attendee;
import com.ems.emsdataservicespring.exceptions.CustomApiException;
import com.ems.emsdataservicespring.exceptions.ExceptionMessage;
import com.ems.emsdataservicespring.repositories.AttendeeRepository;
import com.ems.emsdataservicespring.services.interfaces.AttendeeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendeeServiceImpl implements AttendeeService {

    private static final Logger logger = LoggerFactory.getLogger(AttendeeServiceImpl.class);
    private final AttendeeMapper attendeeMapper;
    private final AttendeeRepository attendeeRepository;
    private static final String ENTITY_NAME = "Attendee";

    @Override
    public AttendeeDto findById(Long id) {
        logger.info("Processing request to find attendee by ID: {}", id);
        Optional<Attendee> attendeeOptional = attendeeRepository.findById(id);
        Attendee attendee = attendeeOptional.orElseThrow(() -> {
            logger.warn("Attendee not found for ID: {}", id);

            return CustomApiException.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(ExceptionMessage.entityNotFound(ENTITY_NAME))
                    .build();
        });

        logger.info("Request to find attendee by ID: {} processed successfully", id);

        return attendeeMapper.mapToDto(attendee);
    }

}
