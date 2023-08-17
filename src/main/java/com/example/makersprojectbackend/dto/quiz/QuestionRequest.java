package com.example.makersprojectbackend.dto.quiz;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionRequest {
    String question; //содержимое вопроса
}
