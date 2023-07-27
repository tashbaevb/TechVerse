package com.example.makersprojectbackend.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SchoolInfoDto {
    Integer schoolNumber;
    String schoolName, grade, location;
}
