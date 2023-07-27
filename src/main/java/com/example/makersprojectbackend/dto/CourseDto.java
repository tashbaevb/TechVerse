package com.example.makersprojectbackend.dto;

import com.example.makersprojectbackend.entity.Lecture;
import com.example.makersprojectbackend.entity.VideoLecture;
import com.example.makersprojectbackend.enums.CourseType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseDto {
    String name, description;
    CourseType courseType;
    List<Lecture> lectures; //лекции
    List<VideoLecture> videoLectures; //видео-лекции
    Integer lectureQuantity; //кол-во видео лекций
    Double duration; //продолжительность курса в часах
}
