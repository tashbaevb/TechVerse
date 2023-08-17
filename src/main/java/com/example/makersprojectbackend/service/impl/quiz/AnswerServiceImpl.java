package com.example.makersprojectbackend.service.impl.quiz;

import com.example.makersprojectbackend.entity.quiz.Answer;
import com.example.makersprojectbackend.entity.quiz.Question;
import com.example.makersprojectbackend.repository.quiz.AnswerRepository;
import com.example.makersprojectbackend.service.quiz.AnswerService;
import com.example.makersprojectbackend.service.quiz.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionService questionService;

    @Override
    public Answer create(Long questionId, Answer answer) {
        Question question = questionService.getById(questionId);
        answer.setQuestion(question);
        return answerRepository.save(answer);
    }

    @Override
    public Answer getById(Long id) {
        return answerRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Answer> getAllAnswersByQuestionId(Long questionId) {
        return answerRepository.findAllByQuestionId(questionId);
    }


    @Override
    public Answer update(Long questionId, Answer details) {
        Answer answer = getById(details.getId());

        Question question = questionService.getById(questionId);
        answer.setQuestion(question);
        answer.setAnswer(details.getAnswer());
        answer.setCorrect(details.getCorrect());
        answer.setQuestion(details.getQuestion());
        return answerRepository.save(answer);
    }

    @Override
    public void delete(Long id) {
        answerRepository.deleteById(id);
    }
}
