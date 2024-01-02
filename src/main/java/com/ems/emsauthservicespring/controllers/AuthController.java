package com.ems.emsauthservicespring.controllers;

import com.ems.emsauthservicespring.entities.dtos.AuthResponseDto;
import com.ems.emsauthservicespring.entities.dtos.LoginUserDto;
import com.ems.emsauthservicespring.entities.dtos.RegisterUserDto;
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
    public AuthResponseDto register(@RequestBody RegisterUserDto registerUserDto) {
        return authService.register(registerUserDto);
    }

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody LoginUserDto loginUserDto) {
        return authService.login(loginUserDto);
    }

}
