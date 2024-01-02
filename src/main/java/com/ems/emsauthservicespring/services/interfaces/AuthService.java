package com.ems.emsauthservicespring.services.interfaces;

import com.ems.emsauthservicespring.entities.dtos.AuthResponseDto;
import com.ems.emsauthservicespring.entities.dtos.LoginUserDto;
import com.ems.emsauthservicespring.entities.dtos.RegisterUserDto;

public interface AuthService {

    AuthResponseDto register(RegisterUserDto registerUserDto);
    AuthResponseDto authenticate(LoginUserDto loginUserDto);

}
