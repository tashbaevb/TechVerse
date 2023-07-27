package com.example.makersprojectbackend.entity;

import com.example.makersprojectbackend.enums.CourseType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
    CourseType courseType; //бесплатный/платный

    @OneToMany(mappedBy = "course")
    List<Lecture> lectures; //лекции

    @OneToMany(mappedBy = "course")
    List<VideoLecture> videoLectures; //видео-лекции

    Double duration; //продолжительность курса в часах
    Integer lectureQuantity; //кол-во видео лекций
    String photoUrl;
    public String getPhotoUrl() {
        return photoUrl;
    }
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
