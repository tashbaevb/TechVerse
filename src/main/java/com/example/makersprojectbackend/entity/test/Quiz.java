package com.example.makersprojectbackend.entity.test;

import com.example.makersprojectbackend.entity.VideoLecture;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

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
    private Map<Integer, Question> questions;

    @OneToOne
    private VideoLecture videoLecture;

}
