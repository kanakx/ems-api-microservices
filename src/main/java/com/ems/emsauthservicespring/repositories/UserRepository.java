package com.ems.emsauthservicespring.repositories;

import com.ems.emsauthservicespring.entities.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
