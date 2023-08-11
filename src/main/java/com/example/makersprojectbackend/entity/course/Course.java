package com.example.makersprojectbackend.entity.course;

import com.example.makersprojectbackend.entity.File;
import com.example.makersprojectbackend.entity.forms.Enroll;
import com.example.makersprojectbackend.enums.CourseType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "courses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name, description;

    BigDecimal price;

    @Enumerated(EnumType.STRING)
    CourseType courseType; //бесплатный/платный

    @OneToMany(mappedBy = "course")
    List<Lecture> lectures; //лекции

    @OneToMany(mappedBy = "course")
    List<VideoLecture> videoLectures; //видео-лекции

    Integer duration; //продолжительность курса в часах
    Integer lectureQuantity; //кол-во видео лекций

    @ManyToOne
    @JoinColumn(name = "enroll_id")
    Enroll enroll;

    @OneToOne
    File file;
}
