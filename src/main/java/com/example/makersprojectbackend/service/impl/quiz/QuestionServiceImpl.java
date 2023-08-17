package com.example.makersprojectbackend.service.impl.quiz;

import com.example.makersprojectbackend.entity.quiz.Question;
import com.example.makersprojectbackend.entity.quiz.Quiz;
import com.example.makersprojectbackend.repository.quiz.QuestionRepository;
import com.example.makersprojectbackend.service.quiz.QuestionService;
import com.example.makersprojectbackend.service.quiz.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final QuizService quizService;

    @Override
    public Question create(Long quizId, Question question) {
        Quiz quiz = quizService.getById(quizId);
        question.setQuiz(quiz);
        return questionRepository.save(question);
    }

    @Override
    public Question update(Long quizId, Question details) {
        Quiz quiz = quizService.getById(quizId);
        Question question = getById(details.getId());
        question.setQuestion(details.getQuestion());
        question.setQuiz(quiz);
        return questionRepository.save(question);
    }

    @Override
    public Question getById(Long id) {
        return questionRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        questionRepository.deleteById(id);
    }
}
