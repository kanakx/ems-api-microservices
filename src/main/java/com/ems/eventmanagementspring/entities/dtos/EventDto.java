package com.ems.eventmanagementspring.entities.dtos;

import com.ems.eventmanagementspring.entities.enums.EventType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EventDto {

    private Long idEvent;
    private String name;
    private EventType type;
    private LocalDateTime startTimestamp;
    private LocalDateTime endTimestamp;
    private String locationName;
    private String description;
    private boolean isPublic;

}
