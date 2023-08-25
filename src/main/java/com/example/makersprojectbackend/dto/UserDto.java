package com.example.makersprojectbackend.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private String fullName;
    private Integer schoolNumber;
    private String schoolName;
    private String schoolGrade;
    private String schoolLocation;
}
