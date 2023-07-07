package com.example.makersprojectbackend.service;

import com.example.makersprojectbackend.entity.test.Question;
import com.example.makersprojectbackend.repository.QuestionRepository;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question create(Question question) {
        return questionRepository.save(question);
    }

    public Question getById(Long id) {
        return questionRepository.findById(id).orElseThrow();
    }
}
