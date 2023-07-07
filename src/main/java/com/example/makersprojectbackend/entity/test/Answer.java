package com.example.makersprojectbackend.entity.test;

import com.example.makersprojectbackend.enums.AnswerType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "answers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text; //содержимое ответа

    private AnswerType answerType; //привальный ответ или нет

    @ManyToOne
    private Question question;
}
