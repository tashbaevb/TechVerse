package com.example.makersprojectbackend.mappers;

import com.example.makersprojectbackend.dto.QuestionDto;
import com.example.makersprojectbackend.entity.test.Question;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionMapper {
    private final ModelMapper mapper;

    public QuestionMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public QuestionDto convertToDto(Question question){
        return mapper.map(question, QuestionDto.class);
    }


}
