package com.example.makersprojectbackend.service.impl;

import com.example.makersprojectbackend.entity.User;
import com.example.makersprojectbackend.entity.course.Course;
import com.example.makersprojectbackend.entity.forms.Enroll;
import com.example.makersprojectbackend.entity.forms.Feedback;
import com.example.makersprojectbackend.enums.CourseType;
import com.example.makersprojectbackend.enums.UserRole;
import com.example.makersprojectbackend.repository.UserRepository;
import com.example.makersprojectbackend.repository.course.CourseRepository;
import com.example.makersprojectbackend.repository.forms.EnrollRepository;
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
    private final EnrollRepository enrollRepository;

    @Override
    public void enrollPaidCourse(Long courseId, Enroll enroll) throws Exception { //подать заявку на платный курс
        Course course = courseRepository.findById(courseId).orElseThrow();
        if (course.getCourseType() == CourseType.PAID) {
            enroll.setCourseName(course.getName());
            enroll.setDateOfCreation(LocalDateTime.now());
            enrollRepository.save(enroll);
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
        user.setUserRole(UserRole.USER);
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
        oldUser.setFullName(newUser.getFullName());
        oldUser.setSchoolNumber(newUser.getSchoolNumber());
        oldUser.setSchoolName(newUser.getSchoolName());
        oldUser.setSchoolGrade(newUser.getSchoolGrade());
        oldUser.setSchoolLocation(newUser.getSchoolLocation());
        return userRepository.save(oldUser);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
