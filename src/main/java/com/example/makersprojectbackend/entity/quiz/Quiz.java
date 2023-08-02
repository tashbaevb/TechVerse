package com.example.makersprojectbackend.entity.quiz;

import com.example.makersprojectbackend.entity.course.VideoLecture;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "quizzes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "quiz")
    private List<Question> questions;

    @OneToOne
    private VideoLecture videoLecture;

}
