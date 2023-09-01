package com.example.makersprojectbackend.dto.course;

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
    String name;
    String description;
    BigDecimal price;
    CourseType courseType;
    List<LectureDto> lectures; //лекции
    List<VideoLectureDto> videoLectures; //видео-лекции
    Integer lectureQuantity; //кол-во видео лекций
    Integer duration; //продолжительность курса в часах
    byte[] file;
}
