package com.example.makersprojectbackend.dto;

import com.example.makersprojectbackend.enums.UserRole;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    String email, password, fullName, schoolName, schoolGrade, schoolLocation;
    Integer schoolNumber;
    UserRole userRole;
}
