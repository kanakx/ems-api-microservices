package com.ems.emsauthservicespring.config.security;

import com.ems.emsauthservicespring.entities.models.User;
import com.ems.emsauthservicespring.exceptions.CustomApiException;
import com.ems.emsauthservicespring.exceptions.ExceptionMessage;
import com.ems.emsauthservicespring.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(username);
        return userOptional
                .orElseThrow(() -> CustomApiException.builder()
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .message(ExceptionMessage.entityNotFound("User"))
                        .build());
    }

}
