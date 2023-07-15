package com.example.makersprojectbackend.repository.test;

import com.example.makersprojectbackend.entity.test.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
