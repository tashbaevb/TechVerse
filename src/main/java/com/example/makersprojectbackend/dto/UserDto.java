package com.example.makersprojectbackend.dto;

import com.example.makersprojectbackend.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private String email;
    private String nameSurname;
    private SchoolInfoDto schoolInfo;
    private UserRole userRole;

}
