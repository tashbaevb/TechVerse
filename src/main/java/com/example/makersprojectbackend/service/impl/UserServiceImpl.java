package com.example.makersprojectbackend.service.impl;

import com.example.makersprojectbackend.entity.Course;
import com.example.makersprojectbackend.entity.SchoolInfo;
import com.example.makersprojectbackend.entity.User;
import com.example.makersprojectbackend.entity.forms.Application;
import com.example.makersprojectbackend.entity.forms.Feedback;
import com.example.makersprojectbackend.enums.CourseType;
import com.example.makersprojectbackend.repository.CourseRepository;
import com.example.makersprojectbackend.repository.UserRepository;
import com.example.makersprojectbackend.repository.forms.ApplicationRepository;
import com.example.makersprojectbackend.repository.forms.FeedbackRepository;
import com.example.makersprojectbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CourseRepository courseRepository;
    private final FeedbackRepository feedbackRepository;
    private final ApplicationRepository applicationRepository;

    @Override
    public void submit(Long courseId, Application application) throws Exception { //подать заявку на платный курс
        Course course = courseRepository.findById(courseId).orElseThrow();
        if (course.getCourseType() == CourseType.PAID) {
            application.setCourseName(course.getName());
            application.setDateOfCreation(LocalDateTime.now());
            applicationRepository.save(application);
        } else throw new Exception("Курс бесплатный");
    }

    @Override
    public ResponseEntity<String> makeFeedback(Feedback feedback) {
        try {
            feedback.setDateOfCreation(LocalDateTime.now());
            feedbackRepository.save(feedback);
            return ResponseEntity.ok("Обращение записано");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при записи обращения");
        }
    }

    @Override
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(User newUser) {
        User oldUser = getById(newUser.getId());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setNameSurname(newUser.getNameSurname());
        SchoolInfo updatedInfo = newUser.getSchoolInfo();
        SchoolInfo existingInfo = oldUser.getSchoolInfo();
        existingInfo.setSchoolNumber(updatedInfo.getSchoolNumber());
        existingInfo.setSchoolName(updatedInfo.getSchoolName());
        existingInfo.setGrade(updatedInfo.getGrade());
        existingInfo.setLocation(updatedInfo.getLocation());
        return userRepository.save(oldUser);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
