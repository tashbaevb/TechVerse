package com.example.makersprojectbackend.dto.course;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VideoLectureDto {

    Long id;

    String title;

    String description;

    String link;

}
