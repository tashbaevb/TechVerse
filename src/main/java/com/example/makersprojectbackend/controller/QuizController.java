package com.example.makersprojectbackend.controller;

import com.example.makersprojectbackend.dto.QuestionDto;
import com.example.makersprojectbackend.mappers.QuestionMapper;
import com.example.makersprojectbackend.service.impl.QuizServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {
    private final QuizServiceImpl quizServiceImpl;
    private final QuestionMapper questionMapper;

    @GetMapping("/{testId}")
    public QuestionDto start(@PathVariable Long testId) {
        return questionMapper.convertToDto(quizServiceImpl.startTest(testId));
    }

    @GetMapping("/{testId}/{currentQuestionIndex}/+")
    public QuestionDto next(@PathVariable Long testId, @PathVariable Integer currentQuestionIndex) {
        return questionMapper.convertToDto(quizServiceImpl.nextQuestion(testId, currentQuestionIndex));
    }

    @GetMapping("/{testId}/{currentQuestionIndex}/-")
    public QuestionDto prev(@PathVariable Long testId, @PathVariable Integer currentQuestionIndex) {
        return questionMapper.convertToDto(quizServiceImpl.previousQuestion(testId, currentQuestionIndex));
    }
}
