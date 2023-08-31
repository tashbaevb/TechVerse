package com.example.makersprojectbackend.entity.course;

import com.example.makersprojectbackend.entity.Favorite;
import com.example.makersprojectbackend.entity.File;
import com.example.makersprojectbackend.entity.User;
import com.example.makersprojectbackend.entity.forms.Enroll;
import com.example.makersprojectbackend.enums.CourseDirection;
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

    @Enumerated(EnumType.STRING)
    CourseDirection courseDirection; //Backend, frontend ...

    @OneToMany(mappedBy = "course")
    List<Lecture> lectures; //лекции

    @OneToMany(mappedBy = "course")
    List<VideoLecture> videoLectures; //видео-лекции

    Integer duration, lectureQuantity; //продолжительность курса в часах, кол-во видео лекций

    @ManyToOne
    @JoinColumn(name = "enroll_id")
    Enroll enroll;

    @OneToOne
    File file;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @OneToOne
    @JoinColumn(name = "favorite_id")
    Favorite favorite;
}
