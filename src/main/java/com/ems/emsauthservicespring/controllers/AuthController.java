package com.ems.emsauthservicespring.controllers;

import com.ems.emsauthservicespring.entities.dtos.*;
import com.ems.emsauthservicespring.services.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public UserDto register(@RequestBody RegisterUserDto registerUserDto) {
        return authService.register(registerUserDto);
    }

    @PostMapping("/login")
    public TokenDto login(@RequestBody LoginUserDto loginUserDto) {
        return authService.login(loginUserDto);
    }

    @PostMapping("/validate")
    public TokenValidationResponseDto validateToken(@RequestBody TokenDto tokenDto) {
        return authService.validateToken(tokenDto);
    }

}
