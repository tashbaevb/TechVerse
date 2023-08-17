package com.example.makersprojectbackend.dto.quiz;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionUpdateDto {
    Long id;
    String question; //содержимое вопроса
}
