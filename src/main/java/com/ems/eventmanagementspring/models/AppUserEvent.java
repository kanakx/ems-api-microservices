package com.ems.eventmanagementspring.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "appUserEvent")
public class AppUserEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_event", updatable = false)
    private Long idAppUserEvent;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    private AppUser appUser;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_event", nullable = false)
    private Event event;

    @Enumerated(EnumType.STRING)
    @Column(length = 32, nullable = false)
    private AppUserEventStatus status;

    @Column(nullable = false)
    private boolean isInvited;

}
