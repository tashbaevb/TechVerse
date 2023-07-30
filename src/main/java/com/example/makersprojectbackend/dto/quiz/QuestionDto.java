package com.example.makersprojectbackend.dto.quiz;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionDto {
    String question; //содержимое вопроса
    Map<Integer, AnswerDto> answers;
}
