package com.example.makersprojectbackend.dto;

import com.example.makersprojectbackend.entity.Lecture;
import com.example.makersprojectbackend.entity.VideoLecture;
import com.example.makersprojectbackend.enums.CourseType;
import lombok.Data;

import java.util.List;

@Data
public class CourseDto {
    private String name;

    private String description;

    private CourseType courseType;

    private List<Lecture> lectures; //лекции

    private List<VideoLecture> videoLectures; //видео-лекции

    private Integer lectureQuantity = videoLectures.size(); //кол-во видео лекций

    private Double duration; //продолжительность курса в часах

}
