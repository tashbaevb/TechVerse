package com.example.makersprojectbackend.mappers;

import com.example.makersprojectbackend.dto.AnswerDto;
import com.example.makersprojectbackend.entity.test.Answer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnswerMapper {
    private final ModelMapper mapper;

    public AnswerMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public List<AnswerDto> convertToDtoList(List<Answer> answers){
        List<AnswerDto> answerDtoList = new ArrayList<>();
        for(Answer a: answers){
            answerDtoList.add(mapper.map(a, AnswerDto.class));
        }
        return answerDtoList;
    }

    public AnswerDto convertToDto(Answer answer){
        return mapper.map(answer, AnswerDto.class);
    }

}
