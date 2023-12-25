package com.ems.eventmanagementspring.entities.models;

import com.ems.eventmanagementspring.entities.enums.MemberEventStatus;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "memberEvent")
public class MemberEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //TODO maybe names attribute can be omitted when is the same as by default
    @Column(name = "idMemberEvent", updatable = false)
    private Long idMemberEvent;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMember", nullable = false)
    private Member member;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_event", nullable = false)
    private Event event;

    @Enumerated(EnumType.STRING)
    @Column(length = 32, nullable = false)
    private MemberEventStatus status;

    @Column(nullable = false)
    private boolean isInvited;

}
