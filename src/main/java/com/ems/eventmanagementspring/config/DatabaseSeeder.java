package com.ems.eventmanagementspring.config;

import com.ems.eventmanagementspring.entities.enums.EventType;
import com.ems.eventmanagementspring.entities.enums.MemberEventStatus;
import com.ems.eventmanagementspring.entities.enums.MemberRole;
import com.ems.eventmanagementspring.entities.models.Event;
import com.ems.eventmanagementspring.entities.models.Member;
import com.ems.eventmanagementspring.entities.models.MemberEvent;
import com.ems.eventmanagementspring.repositories.EventRepository;
import com.ems.eventmanagementspring.repositories.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DatabaseSeeder {

    @Bean
    CommandLineRunner commandLineRunner(
            EventRepository eventRepository,
            MemberRepository memberRepository
    ) {
        return args -> {
            Event event1 = Event.builder()
                    .name("Tech Conference 2023")
                    .type(EventType.CONFERENCE)
                    .startTimestamp(LocalDateTime.parse("2023-03-15T09:00:00"))
                    .endTimestamp(LocalDateTime.parse("2023-03-15T17:00:00"))
                    .locationName("New York")
                    .description("An annual conference for tech enthusiasts and professionals.")
                    .isPublic(true)
                    .build();

            Event event2 = Event.builder()
                    .name("Art & Design Expo")
                    .type(EventType.EXHIBITION)
                    .startTimestamp(LocalDateTime.parse("2023-04-22T10:00:00"))
                    .endTimestamp(LocalDateTime.parse("2023-04-22T18:00:00"))
                    .locationName("San Francisco")
                    .description("Explore the latest trends in art and design.")
                    .isPublic(true)
                    .build();

            Event event3 = Event.builder()
                    .name("Live Music Festival")
                    .type(EventType.FESTIVAL)
                    .startTimestamp(LocalDateTime.parse("2023-05-30T12:00:00"))
                    .endTimestamp(LocalDateTime.parse("2023-05-30T23:00:00"))
                    .locationName("Austin")
                    .description("A festival celebrating the best of live music performances.")
                    .isPublic(false)
                    .build();

            Event event4 = Event.builder()
                    .name("Entrepreneurs Meetup")
                    .type(EventType.MEETUP)
                    .startTimestamp(LocalDateTime.parse("2023-06-10T14:00:00"))
                    .endTimestamp(LocalDateTime.parse("2023-06-10T20:00:00"))
                    .locationName("Chicago")
                    .description("A meetup for aspiring and established entrepreneurs.")
                    .isPublic(true)
                    .build();

            Event event5 = Event.builder()
                    .name("Health and Wellness Workshop")
                    .type(EventType.WORKSHOP)
                    .startTimestamp(LocalDateTime.parse("2023-07-05T09:00:00"))
                    .endTimestamp(LocalDateTime.parse("2023-07-05T16:00:00"))
                    .locationName("Seattle")
                    .description("A workshop focused on health, wellness, and personal growth.")
                    .isPublic(true)
                    .build();

            eventRepository.saveAll(List.of(event1, event2, event3, event4, event5));


            Member member1 = Member.builder()
                    .username("username1")
                    .email("member1@mail.com")
                    .password("$2a$10$Ptba2XLlqTiKOWMMNpcKz.TnDiG8cLyC7TXquMKx6hGEjtoMGoL/S")
                    .role(MemberRole.SPEAKER)
                    .build();
            Member member2 = Member.builder()
                    .username("username2")
                    .email("member2@mail.com")
                    .password("password")
                    .role(MemberRole.ATTENDEE)
                    .build();

//            memberRepository.saveAll(List.of(member1, member2));


            MemberEvent memberEvent1 = MemberEvent.builder()
                    .member(member1)
                    .event(event1)
                    .status(MemberEventStatus.ACCEPTED)
                    .isInvited(false)
                    .build();
            MemberEvent memberEvent2 = MemberEvent.builder()
                    .member(member1)
                    .event(event2)
                    .status(MemberEventStatus.DECLINED)
                    .isInvited(false)
                    .build();
            MemberEvent memberEvent3 = MemberEvent.builder()
                    .member(member2)
                    .event(event3)
                    .status(MemberEventStatus.ACCEPTED)
                    .isInvited(true)
                    .build();

             member1.setMemberEventList(List.of(memberEvent1, memberEvent2));
             member2.setMemberEventList(List.of(memberEvent3));
             memberRepository.saveAll(List.of(member1, member2));
        };
    }
}
