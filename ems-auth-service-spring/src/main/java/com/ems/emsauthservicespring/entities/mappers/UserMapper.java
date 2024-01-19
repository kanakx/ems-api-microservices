package com.ems.emsauthservicespring.entities.mappers;

import com.ems.emsauthservicespring.entities.dtos.UserDto;
import com.ems.emsauthservicespring.entities.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto mapToDto(User user);

}
