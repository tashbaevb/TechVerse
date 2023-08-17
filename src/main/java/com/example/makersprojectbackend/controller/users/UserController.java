package com.example.makersprojectbackend.controller.users;

import com.example.makersprojectbackend.dto.UserDto;
import com.example.makersprojectbackend.dto.course.FreeCourseDto;
import com.example.makersprojectbackend.entity.User;
import com.example.makersprojectbackend.entity.course.Course;
import com.example.makersprojectbackend.entity.forms.Enroll;
import com.example.makersprojectbackend.entity.forms.Feedback;
import com.example.makersprojectbackend.enums.CourseType;
import com.example.makersprojectbackend.mappers.CourseMapper;
import com.example.makersprojectbackend.mappers.UserMapper;
import com.example.makersprojectbackend.repository.UserRepository;
import com.example.makersprojectbackend.repository.course.CourseRepository;
import com.example.makersprojectbackend.service.UserService;
import com.example.makersprojectbackend.service.course.CourseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "get personal info, make feedback/enroll(sub), get courses")
public class UserController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final UserService userService;
    private final CourseRepository courseRepository;

    // ЛИЧНЫЙ КАБИНЕТ
    @GetMapping("/personal-info")
    public @ResponseBody UserDto showPersonalInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        Optional<User> user = userRepository.findByEmail(userEmail);
        return userMapper.convertToDtoOpt(user);
    }


    @PutMapping("/user/update/")
    public UserDto update(@RequestBody UserDto dto) {
        User user = userMapper.convertToEntity(dto);
        return userMapper.convertToDto(userService.update(user));
    }


    // ЗАЯВКИ И ЗАПИСЬ
    @PutMapping("/subscribe/{courseId}") //записаться на курс
    public void subscribe(@PathVariable Long courseId, @RequestBody Enroll enroll) throws Exception {
        userService.enrollPaidCourse(courseId, enroll);
    }


    @PutMapping("/feedback") //связаться
    public ResponseEntity<String> feedback(@RequestBody Feedback feedback) {
        return userService.makeFeedback(feedback);
    }


    // COURSE
    @GetMapping("/course/get/{id}")
    public FreeCourseDto getCourseById(@PathVariable Long id) {
        return courseMapper.convertToFreeCourseDto(courseService.getById(id));
    }


    @GetMapping("/course/get")
    public List<Course> getAllPaidCourses(@RequestParam(required = false) CourseType courseType) {
        return courseService.getCoursesByType(courseType);
    }


    @GetMapping("/course/get/all")
    public List<Course> getAll() {
        return courseService.getAll();
    }


    // SEARCH
    @GetMapping("/course/search")
    public List<Course> findCourses(@RequestParam(required = false) String name) {
        return courseService.findCoursesWithFuzzyName(name);
    }
}