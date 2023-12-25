package com.ems.eventmanagementspring.repositories;

import com.ems.eventmanagementspring.entities.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {



}
