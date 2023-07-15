package com.example.makersprojectbackend.service;

import com.example.makersprojectbackend.entity.test.Question;
import com.example.makersprojectbackend.entity.test.Quiz;

import java.util.List;

public interface QuizService {

    Quiz create(Quiz quiz);

    Quiz getById(Long id);

    List<Quiz> getAll();

    Quiz update(Quiz quizDetails);

    void delete(Long id);

    Question startTest(Long testId);

    Question nextQuestion(Long testId, int currentQuestionIndex);

    Question previousQuestion(Long testId, int currentQuestionIndex);
}
