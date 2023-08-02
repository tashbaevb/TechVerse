package com.example.makersprojectbackend.dto.course;

import com.example.makersprojectbackend.entity.quiz.Quiz;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VideoLectureDto {

    String link;

    FreeCourseDto course;

    Quiz quiz;
}
