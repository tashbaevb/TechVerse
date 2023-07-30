package com.example.makersprojectbackend.repository.quiz;

import com.example.makersprojectbackend.entity.quiz.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
