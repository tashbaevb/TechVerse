package com.example.makersprojectbackend.dto;

import lombok.Data;

import java.util.Map;

@Data
public class QuestionDto {
    private String question; //содержимое вопроса

    private Map<Integer, AnswerDto> answers;

}
