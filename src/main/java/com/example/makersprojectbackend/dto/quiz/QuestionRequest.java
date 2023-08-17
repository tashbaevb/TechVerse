package com.example.makersprojectbackend.dto.quiz;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionDto {
    Long id;
    String question; //содержимое вопроса
    List<AnswerDto> answers;
}
