package com.example.makersprojectbackend.service;

import com.example.makersprojectbackend.entity.Course;
import com.example.makersprojectbackend.entity.User;
import com.example.makersprojectbackend.entity.forms.Feedback;
import com.example.makersprojectbackend.entity.forms.Application;
import com.example.makersprojectbackend.enums.CourseType;
import com.example.makersprojectbackend.repository.CourseRepository;
import com.example.makersprojectbackend.repository.UserRepository;
import com.example.makersprojectbackend.repository.forms.FeedbackRepository;
import com.example.makersprojectbackend.repository.forms.ApplicationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final FeedbackRepository feedbackRepository;
    private final ApplicationRepository applicationRepository;

    public UserService(UserRepository userRepository, CourseRepository courseRepository, FeedbackRepository feedbackRepository, ApplicationRepository applicationRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.feedbackRepository = feedbackRepository;
        this.applicationRepository = applicationRepository;
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User update(User userDetails) {
        User user = getById(userDetails.getId());
        user.setFullName(userDetails.getFullName());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setLink(userDetails.getLink());
        user.setSchool(userDetails.getSchool());
        user.setUsername(userDetails.getUsername());
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public void submit(Long courseId, Application application) throws Exception { //подать заявку на платный курс
        Course course = courseRepository.findById(courseId).orElseThrow();
        if (course.getCourseType() == CourseType.PAID) {
            application.setCourseName(course.getName());
            applicationRepository.save(application);
        } else throw new Exception("Курс бесплатный");
    }

    public ResponseEntity<String> makeAppeal(Feedback feedback) {
        try {
            feedbackRepository.save(feedback);
            return ResponseEntity.ok("Обращение записано");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при записи обращения");
        }
    }
}
