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
                    .name("event1")
                    .type(EventType.ACTIVE)
                    .startTimestamp(LocalDateTime.now())
                    .endTimestamp(LocalDateTime.now().plusDays(1))
                    .locationName("location1")
                    .description("description1")
                    .isPublic(true)
                    .build();
            Event event2 = Event.builder()
                    .name("event2")
                    .type(EventType.CANCELLED)
                    .startTimestamp(LocalDateTime.now())
                    .endTimestamp(LocalDateTime.now().plusDays(1))
                    .locationName("location1")
                    .description("description2")
                    .isPublic(true)
                    .build();
            Event event3 = Event.builder()
                    .name("event3")
                    .type(EventType.ACTIVE)
                    .startTimestamp(LocalDateTime.now())
                    .endTimestamp(LocalDateTime.now().plusDays(1))
                    .locationName("location3")
                    .description("description2")
                    .isPublic(false)
                    .build();

            eventRepository.saveAll(List.of(event1, event2, event3));


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
                    .isInvited(true)
                    .build();
            MemberEvent memberEvent2 = MemberEvent.builder()
                    .member(member1)
                    .event(event2)
                    .status(MemberEventStatus.DECLINED)
                    .isInvited(true)
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
