package com.example.makersprojectbackend.mappers.quiz;

import com.example.makersprojectbackend.dto.quiz.QuizDto;
import com.example.makersprojectbackend.dto.quiz.QuizRequest;
import com.example.makersprojectbackend.entity.quiz.Quiz;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class QuizMapper {
    private ModelMapper mapper;

    public QuizDto convertToDto(Quiz quiz) {
        return mapper.map(quiz, QuizDto.class);
    }

    public Quiz convertToEntity(QuizDto quizDto) {
        return mapper.map(quizDto, Quiz.class);
    }

    public Quiz convertToEntity(QuizRequest request) {
        return mapper.map(request, Quiz.class);
    }

    public List<QuizDto> convertToDtoList(List<Quiz> quizzes) {
        List<QuizDto> quizDtoList = new ArrayList<>();
        for (Quiz q : quizzes) {
            quizDtoList.add(convertToDto(q));
        }
        return quizDtoList;
    }
}
