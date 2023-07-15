package com.example.makersprojectbackend.entity.test;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Entity
@Table(name = "questions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question; //содержимое вопроса

    @OneToMany(mappedBy = "question")
    private Map<Integer, Answer> answers;

    @ManyToOne
    private Quiz quiz;

}
