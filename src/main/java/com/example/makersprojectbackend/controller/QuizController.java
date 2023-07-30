package com.example.makersprojectbackend.controller;

import com.example.makersprojectbackend.dto.quiz.QuestionDto;
import com.example.makersprojectbackend.mappers.quiz.QuestionMapper;
import com.example.makersprojectbackend.service.quiz.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;
    private final QuestionMapper questionMapper;

    @GetMapping("/{testId}")
    public QuestionDto start(@PathVariable Long testId) {
        return questionMapper.convertToDto(quizService.startTest(testId));
    }

    @GetMapping("/{testId}/{currentQuestionIndex}/next")
    public QuestionDto next(@PathVariable Long testId, @PathVariable Integer currentQuestionIndex) {
        return questionMapper.convertToDto(quizService.nextQuestion(testId, currentQuestionIndex));
    }

    @GetMapping("/{testId}/{currentQuestionIndex}/prev")
    public QuestionDto prev(@PathVariable Long testId, @PathVariable Integer currentQuestionIndex) {
        return questionMapper.convertToDto(quizService.previousQuestion(testId, currentQuestionIndex));
    }

    @GetMapping("/res/{quizId}")
    public String showResults(@PathVariable Long quizId, @RequestParam Long[] answersIds) {
        return quizService.showResults(quizId, answersIds);
    }
}
