package com.example.makersprojectbackend.service;

import com.example.makersprojectbackend.entity.test.Question;

public interface QuestionService {

    Question create(Question question);

    Question getById(Long id);
}
