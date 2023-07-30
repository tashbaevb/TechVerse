package com.example.makersprojectbackend.repository.quiz;

import com.example.makersprojectbackend.entity.quiz.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
