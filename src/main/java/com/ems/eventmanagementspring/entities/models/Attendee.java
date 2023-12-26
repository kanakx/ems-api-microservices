package com.ems.eventmanagementspring.entities.models;

import com.ems.eventmanagementspring.entities.enums.MemberRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Attendee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long idAttendee;

    @Column(length = 100, nullable = false)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(length = 100, nullable = false)
    private MemberRole role;

    @OneToMany(mappedBy = "attendee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AttendeeEvent> attendeeEventList = new ArrayList<>();

}
