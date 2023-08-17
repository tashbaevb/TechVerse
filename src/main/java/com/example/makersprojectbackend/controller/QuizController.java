package com.example.makersprojectbackend.controller;

import com.example.makersprojectbackend.dto.quiz.QuestionDto;
import com.example.makersprojectbackend.mappers.quiz.QuestionMapper;
import com.example.makersprojectbackend.service.quiz.QuizService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
@Tag(name = "Quiz", description = "start, next, prev, show results - for user")
public class QuizController {

    private final QuizService quizService;
    private final QuestionMapper questionMapper;


    @GetMapping("/start/{quizId}")
    public QuestionDto start(@PathVariable Long quizId) {
        return questionMapper.convertToDto(quizService.startTest(quizId));
    }

    @GetMapping("/next/{quizId}/{currentQuestionIndex}")
    public QuestionDto next(@PathVariable Long quizId, @PathVariable Integer currentQuestionIndex) {
        return questionMapper.convertToDto(quizService.nextQuestion(quizId, currentQuestionIndex));
    }

    @GetMapping("/prev/{quizId}/{currentQuestionIndex}/prev")
    public QuestionDto prev(@PathVariable Long quizId, @PathVariable Integer currentQuestionIndex) {
        return questionMapper.convertToDto(quizService.previousQuestion(quizId, currentQuestionIndex));
    }

    @GetMapping("/res/{quizId}")
    public String showResults(@PathVariable Long quizId, @RequestParam Long[] answersIds) {
        return quizService.showResults(quizId, answersIds);
    }
}
