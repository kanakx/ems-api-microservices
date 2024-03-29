package com.ems.emsdataservicespring.services.implementations;

import com.ems.emsdataservicespring.entities.dtos.AttendeeDto;
import com.ems.emsdataservicespring.entities.mappers.AttendeeMapper;
import com.ems.emsdataservicespring.entities.models.Attendee;
import com.ems.emsdataservicespring.exceptions.CustomApiException;
import com.ems.emsdataservicespring.repositories.AttendeeRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AttendeeServiceImplTest {

    @Mock
    AttendeeMapper attendeeMapper;

    @Mock
    AttendeeRepository attendeeRepository;

    @InjectMocks
    AttendeeServiceImpl attendeeService;

    private final EasyRandom easyRandom = new EasyRandom();

    @Test
    void findById_Success() {
        // Given
        Long id = easyRandom.nextLong();
        Attendee attendee = easyRandom.nextObject(Attendee.class);

        AttendeeDto attendeeDto = easyRandom.nextObject(AttendeeDto.class);

        when(attendeeRepository.findById(id)).thenReturn(Optional.of(attendee));
        when(attendeeMapper.mapToDto(attendee)).thenReturn(attendeeDto);

        // When
        AttendeeDto serviceResult = attendeeService.findById(id);

        // Then
        assertEquals(attendeeDto, serviceResult);
        verify(attendeeRepository, times(1)).findById(id);
        verify(attendeeMapper, times(1)).mapToDto(attendee);
    }

    @Test
    void findById_Failure_AttendeeNotFound() {
        // Given
        Long id = easyRandom.nextLong();
        when(attendeeRepository.findById(id)).thenReturn(Optional.empty());

        // When
        // Then
        assertThrows(CustomApiException.class, () -> attendeeService.findById(id));
        verify(attendeeRepository, times(1)).findById(id);
        verify(attendeeMapper, never()).mapToDto(any(Attendee.class));
    }

}
