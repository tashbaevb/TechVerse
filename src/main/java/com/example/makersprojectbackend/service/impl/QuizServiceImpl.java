package com.example.makersprojectbackend.service.impl;

import com.example.makersprojectbackend.entity.test.Question;
import com.example.makersprojectbackend.entity.test.Quiz;
import com.example.makersprojectbackend.repository.test.QuizRepository;
import com.example.makersprojectbackend.service.QuizService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;


    public QuizServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;

    }

    @Override
    public Quiz create(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public Quiz getById(Long id) {
        return quizRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Quiz> getAll() {
        return quizRepository.findAll();
    }

    @Override
    public Quiz update(Quiz quizDetails) {
        Quiz quiz = getById(quizDetails.getId());
        quiz.setTitle(quizDetails.getTitle());
        quiz.setQuestions(quizDetails.getQuestions());
        quiz.setVideoLecture(quizDetails.getVideoLecture());
        return quizRepository.save(quiz);
    }

    @Override
    public void delete(Long id) {
        quizRepository.deleteById(id);
    }

    @Override
    public Question startTest(Long testId){
        Quiz quiz = quizRepository.findById(testId).orElseThrow();
        return quiz.getQuestions().get(1);
    }

    @Override
    public Question nextQuestion(Long testId, int currentQuestionIndex){
        Quiz quiz = quizRepository.findById(testId).orElseThrow();
        return quiz.getQuestions().get(currentQuestionIndex + 1);
    }

    @Override
    public Question previousQuestion(Long testId, int currentQuestionIndex){
        Quiz quiz = quizRepository.findById(testId).orElseThrow();
        return quiz.getQuestions().get(currentQuestionIndex - 1);
    }
}
