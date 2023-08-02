package com.example.makersprojectbackend.mappers.quiz;

import com.example.makersprojectbackend.dto.quiz.QuestionDto;
import com.example.makersprojectbackend.entity.quiz.Question;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {
    private final ModelMapper mapper;

    public QuestionMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public QuestionDto convertToDto(Question question) {
        return mapper.map(question, QuestionDto.class);
    }


}
