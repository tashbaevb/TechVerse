package com.example.makersprojectbackend.dto;

import com.example.makersprojectbackend.enums.UserRole;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    String fullName, username, email, link, school, nameSurname;
    SchoolInfoDto schoolInfo;
    UserRole userRole;
}
