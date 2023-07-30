package com.example.makersprojectbackend.service.impl.quiz;

import com.example.makersprojectbackend.entity.quiz.Question;
import com.example.makersprojectbackend.repository.quiz.QuestionRepository;
import com.example.makersprojectbackend.service.quiz.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    @Override
    public Question create(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question getById(Long id) {
        return questionRepository.findById(id).orElseThrow();
    }
}
