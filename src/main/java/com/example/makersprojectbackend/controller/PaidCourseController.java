package com.example.makersprojectbackend.controller;

import com.example.makersprojectbackend.models.Enroll;
import com.example.makersprojectbackend.models.PaidCourse;
import com.example.makersprojectbackend.service.EnrollService;
import com.example.makersprojectbackend.service.PaidCourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paidcourses")
public class PaidCourseController {
    private final PaidCourseService paidCourseService;
    private final EnrollService enrollService;

    public PaidCourseController(PaidCourseService paidCourseService, EnrollService enrollService) {
        this.paidCourseService = paidCourseService;
        this.enrollService = enrollService;
    }

    @GetMapping
    public List<PaidCourse> getAllCourses() {
        return paidCourseService.getAllPaidCourses();
    }

    @GetMapping("/enrolls")
    public List<Enroll> getAllEnrolls() {
        return enrollService.getAllEnrolls();
    }

    @PostMapping("/enrolls")
    public ResponseEntity<String> enrollPaidCourse(@RequestBody Enroll enroll) {
        enrollService.enrollPaidCourse(enroll);
        return ResponseEntity.status(HttpStatus.CREATED).body("Вы успешно записались на платный курс.");
    }
}
