package com.example.makersprojectbackend.controller;

import com.example.makersprojectbackend.dto.course.PaidCourseDto;
import com.example.makersprojectbackend.entity.forms.Enroll;
import com.example.makersprojectbackend.mappers.CourseMapper;
import com.example.makersprojectbackend.service.UserService;
import com.example.makersprojectbackend.service.course.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paidcourses")
@RequiredArgsConstructor
public class PaidCourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;
    private final UserService userService;

    @GetMapping
    public List<PaidCourseDto> getAllCourses() {
        return courseMapper.convertToPaidCourseDtoList(courseService.getAll());
    }


    @PostMapping("/enroll/{courseId}")
    public ResponseEntity<String> enrollPaidCourse(@PathVariable Long courseId, @RequestBody Enroll enroll) throws Exception {
        userService.enrollPaidCourse(courseId, enroll);
        return ResponseEntity.status(HttpStatus.CREATED).body("Вы успешно записались на платный курс.");
    }
}