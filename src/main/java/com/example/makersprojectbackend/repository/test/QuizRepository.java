package com.example.makersprojectbackend.repository.test;

import com.example.makersprojectbackend.entity.test.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
