package com.ems.emsauthservicespring.entities.dtos;

import com.ems.emsauthservicespring.entities.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long idUser;
    private String email;
    private UserRole userRole;

}
