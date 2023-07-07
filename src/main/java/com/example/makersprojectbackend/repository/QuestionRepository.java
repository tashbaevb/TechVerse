package com.example.makersprojectbackend.repository;

import com.example.makersprojectbackend.entity.test.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
