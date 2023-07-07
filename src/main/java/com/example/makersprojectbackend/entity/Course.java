package com.example.makersprojectbackend.entity;

import com.example.makersprojectbackend.enums.CourseType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "courses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private CourseType courseType; //платный/бесплатный

    @OneToMany(mappedBy = "course")
    private List<Lecture> lectures; //лекции

    @OneToMany(mappedBy = "course")
    private List<VideoLecture> videoLectures; //видео-лекции

    private Double duration; //продолжительность курса в часах

    private Integer lectureQuantity; //кол-во видео лекций

}
