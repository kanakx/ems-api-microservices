package com.ems.emsauthservicespring.services.implementations;

import com.ems.emsauthservicespring.config.JwtService;
import com.ems.emsauthservicespring.entities.dtos.AuthResponseDto;
import com.ems.emsauthservicespring.entities.dtos.LoginUserDto;
import com.ems.emsauthservicespring.entities.dtos.RegisterUserDto;
import com.ems.emsauthservicespring.entities.enums.UserRole;
import com.ems.emsauthservicespring.entities.models.User;
import com.ems.emsauthservicespring.exceptions.CustomApiException;
import com.ems.emsauthservicespring.exceptions.ExceptionMessage;
import com.ems.emsauthservicespring.repositories.UserRepository;
import com.ems.emsauthservicespring.services.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponseDto register(RegisterUserDto registerUserDto) {
        userRepository.findByEmail(registerUserDto.getEmail()).ifPresent(user -> {
            throw CustomApiException.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message(ExceptionMessage.entityAlreadyExists("Event"))
                    .build();
        });

        User user = User.builder()
                .email(registerUserDto.getEmail())
                .password(registerUserDto.getPassword())
                .userRole(UserRole.USER)
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user);
        return AuthResponseDto.builder()
                .token(token)
                .build();
    }

    @Override
    public AuthResponseDto login(LoginUserDto loginUserDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUserDto.getEmail(), loginUserDto.getPassword())
        );

        User user = userRepository.findByEmail(loginUserDto.getEmail()).orElseThrow(() ->
                CustomApiException.builder()
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .message(ExceptionMessage.entityNotFound("User"))
                        .build()
        );

        String token = jwtService.generateToken(user);

        return AuthResponseDto.builder()
                .token(token)
                .build();
    }

}
