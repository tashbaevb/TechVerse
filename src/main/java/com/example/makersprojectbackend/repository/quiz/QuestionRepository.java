package com.example.makersprojectbackend.repository.quiz;

import com.example.makersprojectbackend.entity.quiz.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
