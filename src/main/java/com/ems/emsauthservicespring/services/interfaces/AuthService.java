package com.ems.emsauthservicespring.services.interfaces;

import com.ems.emsauthservicespring.entities.dtos.TokenDto;
import com.ems.emsauthservicespring.entities.dtos.LoginUserDto;
import com.ems.emsauthservicespring.entities.dtos.RegisterUserDto;
import com.ems.emsauthservicespring.entities.dtos.TokenValidationResponseDto;

public interface AuthService {

    TokenDto register(RegisterUserDto registerUserDto);
    TokenDto login(LoginUserDto loginUserDto);
    TokenValidationResponseDto validateToken(TokenDto tokenDto);

}
