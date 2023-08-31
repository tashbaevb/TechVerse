package com.example.makersprojectbackend.service.impl.quiz;

import com.example.makersprojectbackend.entity.course.VideoLecture;
import com.example.makersprojectbackend.entity.quiz.Answer;
import com.example.makersprojectbackend.entity.quiz.Question;
import com.example.makersprojectbackend.entity.quiz.Quiz;
import com.example.makersprojectbackend.repository.course.VideoLectureRepository;
import com.example.makersprojectbackend.repository.quiz.AnswerRepository;
import com.example.makersprojectbackend.repository.quiz.QuizRepository;
import com.example.makersprojectbackend.service.quiz.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;
    private final AnswerRepository answerRepository;
    private final VideoLectureRepository videoLectureRepository;

    @Override
    public Quiz create(Long videoLectureId, Quiz quiz) {
        VideoLecture videoLecture = videoLectureRepository.findById(videoLectureId).orElseThrow();
        videoLecture.setQuiz(quiz);
        quiz.setVideoLecture(videoLecture);
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
    public Question startTest(Long testId) {
        Quiz quiz = quizRepository.findById(testId).orElseThrow();
        return quiz.getQuestions().get(1);
    }

    @Override
    public Question nextQuestion(Long testId, int currentQuestionIndex) {
        Quiz quiz = quizRepository.findById(testId).orElseThrow();
        return quiz.getQuestions().get(currentQuestionIndex + 1);
    }

    @Override
    public Question previousQuestion(Long testId, int currentQuestionIndex) {
        Quiz quiz = quizRepository.findById(testId).orElseThrow();
        return quiz.getQuestions().get(currentQuestionIndex - 1);
    }

    @Override
    public String showResults(Long quizId, Long[] answersIds) {

        List<Answer> correctAnswers = new ArrayList<>();
        for (Long answerId : answersIds) {
            Answer answer = answerRepository.findById(answerId).orElseThrow();
            if (answer.getCorrect()) {
                correctAnswers.add(answer);
            }
        }
        Quiz quiz = getById(quizId);
        int totalAnswers = 0;
        for (Question q : quiz.getQuestions()) {
            totalAnswers = totalAnswers + q.getAnswers().size();
        }
        BigDecimal corrects = new BigDecimal(correctAnswers.size());
        BigDecimal total = new BigDecimal(totalAnswers);
        BigDecimal percent = new BigDecimal(100);
        BigDecimal result = corrects.divide(total, 2, RoundingMode.HALF_UP).multiply(percent);
        return correctAnswers.size() + "/" + totalAnswers + "\n\n" + "Процент верных ответов: " + result + "%";

    }
}
