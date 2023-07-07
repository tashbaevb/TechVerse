package com.example.makersprojectbackend.repository.forms;

import com.example.makersprojectbackend.entity.forms.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
