package com.example.makersprojectbackend.dto.course;

import com.example.makersprojectbackend.enums.CourseDirection;
import com.example.makersprojectbackend.enums.CourseType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaidCourseDto {
    Long id;
    String name, description;
    BigDecimal price;
    CourseType courseType;
    List<LectureDto> lectures; //лекции
    List<VideoLectureDto> videoLectures; //видео-лекции
    Integer lectureQuantity, duration; //кол-во видео лекций / продолжительность курса в часах
    CourseDirection courseDirection;
    byte[] file;
}
