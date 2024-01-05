package com.ems.emsauthservicespring.services.interfaces;

import com.ems.emsauthservicespring.entities.dtos.*;

public interface AuthService {

    UserDto register(RegisterUserDto registerUserDto);
    TokenDto login(LoginUserDto loginUserDto);
    TokenValidationResponseDto validateToken(TokenDto tokenDto);

}
