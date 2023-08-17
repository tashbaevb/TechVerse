package com.example.makersprojectbackend.mappers.quiz;

import com.example.makersprojectbackend.dto.quiz.QuestionDto;
import com.example.makersprojectbackend.dto.quiz.QuestionRequest;
import com.example.makersprojectbackend.dto.quiz.QuestionUpdateDto;
import com.example.makersprojectbackend.entity.quiz.Question;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class QuestionMapper {
    private ModelMapper mapper;

    public QuestionDto convertToDto(Question question) {
        return mapper.map(question, QuestionDto.class);
    }

    public Question convertToEntity(QuestionRequest request) {
        return mapper.map(request, Question.class);
    }

    public Question convertToEntity(QuestionUpdateDto questionDto) {
        return mapper.map(questionDto, Question.class);
    }

    public List<QuestionDto> convertToDtoList(List<Question> questions) {
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (Question q : questions) {
            questionDtoList.add(convertToDto(q));
        }
        return questionDtoList;
    }

}
