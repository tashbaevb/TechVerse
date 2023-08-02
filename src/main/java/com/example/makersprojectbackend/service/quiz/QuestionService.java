package com.example.makersprojectbackend.service.quiz;

import com.example.makersprojectbackend.entity.quiz.Question;

public interface QuestionService {

    Question create(Question question);

    Question getById(Long id);
}
