package com.example.makersprojectbackend.dto.quiz;

import lombok.Data;

@Data
public class AnswerRequest {
    private String answer;
    private Boolean correct;
}
