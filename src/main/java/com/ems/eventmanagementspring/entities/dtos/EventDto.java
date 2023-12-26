package com.ems.eventmanagementspring.entities.dtos;

import com.ems.eventmanagementspring.entities.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {

    private Long idEvent;
    private String name;
    private EventType type;
    private LocalDateTime startTimestamp;
    private LocalDateTime endTimestamp;
    private String locationName;
    private String description;
    private Boolean isPublic;

}
