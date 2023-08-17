package com.example.makersprojectbackend.service.quiz;

import com.example.makersprojectbackend.entity.quiz.Answer;

import java.util.List;

public interface AnswerService {
    Answer create(Long questionId, Answer answer);

    Answer getById(Long id);

    List<Answer> getAllAnswersByQuestionId(Long questionId);


    Answer update(Long questionId, Answer details);

    void delete(Long id);
}
