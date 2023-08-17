package com.example.makersprojectbackend.service.quiz;

import com.example.makersprojectbackend.entity.quiz.Question;

import java.util.List;

public interface QuestionService {


    Question create(Long quizId, Question question);

    Question update(Long quizId, Question question);


    Question getById(Long id);

    List<Question> getAll();

    void delete(Long id);
}
