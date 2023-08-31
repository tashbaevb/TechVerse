package com.example.makersprojectbackend.dto.course;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LectureDto {
    Long id;
    String title;

    String text;
}
