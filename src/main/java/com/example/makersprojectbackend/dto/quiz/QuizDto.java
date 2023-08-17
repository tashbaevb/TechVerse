package com.example.makersprojectbackend.mappers.quiz;

import com.example.makersprojectbackend.dto.quiz.QuestionDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class QuizDto {
    private Long id;
    private String title;
}