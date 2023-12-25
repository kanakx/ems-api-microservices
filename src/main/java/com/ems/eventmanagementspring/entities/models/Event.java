package com.ems.eventmanagementspring.entities.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_event", updatable = false)
    private Long idEvent;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    @Enumerated(EnumType.STRING)
    private EventType type;

    @Column(nullable = false)
    private LocalDateTime startTimestamp;

    @Column(nullable = false)
    private LocalDateTime endTimestamp;

    @Column(length = 100, nullable = false)
    private String locationName;

    @Column(length = 250, nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean isPublic;

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MemberEvent> memberEvents;

}
