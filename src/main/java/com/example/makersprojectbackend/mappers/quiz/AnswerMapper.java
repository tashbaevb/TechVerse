package com.example.makersprojectbackend.mappers.quiz;

import com.example.makersprojectbackend.dto.quiz.AnswerDto;
import com.example.makersprojectbackend.dto.quiz.AnswerRequest;
import com.example.makersprojectbackend.entity.quiz.Answer;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class AnswerMapper {
    private ModelMapper mapper;


    public AnswerDto convertToDto(Answer answer) {
        return mapper.map(answer, AnswerDto.class);
    }

    public Answer convertToEntity(AnswerDto answer) {
        return mapper.map(answer, Answer.class);
    }

    public Answer convertToEntity(AnswerRequest request) {
        return mapper.map(request, Answer.class);
    }

    public List<AnswerDto> convertToDtoList(List<Answer> answers) {
        List<AnswerDto> answerDtoList = new ArrayList<>();
        for (Answer a : answers) {
            answerDtoList.add(mapper.map(a, AnswerDto.class));
        }
        return answerDtoList;
    }


}
