package com.example.makersprojectbackend.dto.course;

import com.example.makersprojectbackend.enums.CourseDirection;
import com.example.makersprojectbackend.enums.CourseType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FreeCourseDto {
    Long id;
    String name, description;
    CourseType courseType;
    List<LectureDto> lectures; //лекции
    List<VideoLectureDto> videoLectures; //видео-лекции
    Integer lectureQuantity; //кол-во видео лекций
    Integer duration; //продолжительность курса в часах
    CourseDirection courseDirection;
    byte[] file;
}
