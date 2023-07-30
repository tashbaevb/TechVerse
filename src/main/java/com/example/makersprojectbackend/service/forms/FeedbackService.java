package com.example.makersprojectbackend.service.forms;

import com.example.makersprojectbackend.entity.forms.Feedback;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FeedbackService {

    Feedback create(Feedback feedback);

    Feedback getById(Long id);

    List<Feedback> getAll();

    void delete(Long id);

    ResponseEntity<byte[]> exportToExcel(List<Feedback> feedbacks);
}
