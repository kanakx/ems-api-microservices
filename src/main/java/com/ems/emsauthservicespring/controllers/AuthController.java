package com.ems.emsauthservicespring.controllers;

import com.ems.emsauthservicespring.entities.dtos.*;
import com.ems.emsauthservicespring.services.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    @PostMapping("/register")
    public UserDto register(@RequestBody RegisterUserDto registerUserDto) {
        logger.info("Registering attempt for a new user with email: {}", registerUserDto.getEmail());
        UserDto userDto = authService.register(registerUserDto);
        logger.info("Registration successful for email: {}", userDto.getEmail());
        return userDto;
    }

    @PostMapping("/login")
    public TokenDto login(@RequestBody LoginUserDto loginUserDto) {
        logger.info("Login attempt for email: {}", loginUserDto.getEmail());
        TokenDto tokenDto = authService.login(loginUserDto);
        logger.info("Login successful for email: {}", loginUserDto.getEmail());
        return tokenDto;
    }

    @PostMapping("/validate")
    public TokenValidationResponseDto validateToken(@RequestBody TokenDto tokenDto) {
        logger.debug("Validating token");
        TokenValidationResponseDto validationResponse = authService.validateToken(tokenDto);
        logger.debug("Token validation status: {}", validationResponse.getIsValid());
        return validationResponse;
    }

}
