package com.example.makersprojectbackend.service.quiz;

import com.example.makersprojectbackend.entity.quiz.Question;
import com.example.makersprojectbackend.entity.quiz.Quiz;

import java.util.List;

public interface QuizService {

    Quiz create(Long videoLectureId, Quiz quiz);

    Quiz getById(Long id);

    List<Quiz> getAll();

    Quiz update(Quiz quizDetails);

    void delete(Long id);

    Question startTest(Long testId);

    Question nextQuestion(Long testId, int currentQuestionIndex);

    Question previousQuestion(Long testId, int currentQuestionIndex);

    String showResults(Long quizId, Long[] answersIds);
}
