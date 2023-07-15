package com.example.makersprojectbackend.dto;

import com.example.makersprojectbackend.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private String email;
    private String password;
    private String phoneNumber;
    private UserRole userRole;

}
