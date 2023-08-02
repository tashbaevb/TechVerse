package com.example.makersprojectbackend.service;

import com.example.makersprojectbackend.entity.User;
import com.example.makersprojectbackend.entity.forms.Enroll;
import com.example.makersprojectbackend.entity.forms.Feedback;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    User create(User user);

    User getById(Long id);

    User update(User newUser);

    List<User> getAll();

    void delete(Long id);

    void enrollPaidCourse(Long courseId, Enroll enroll) throws Exception;

    ResponseEntity<String> makeFeedback(Feedback feedback);

}
