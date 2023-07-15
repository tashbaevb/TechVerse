package com.makers.TechVerse.controller;

import com.makers.TechVerse.models.Enroll;
import com.makers.TechVerse.models.PaidCourse;
import com.makers.TechVerse.service.EnrollService;
import com.makers.TechVerse.service.PaidCourseService;
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

    @PostMapping("/api")
    public ResponseEntity<String> enrollPaidCourse(@RequestBody Enroll enroll) {
        enrollService.enrollPaidCourse(enroll);
        return ResponseEntity.status(HttpStatus.CREATED).body("Вы успешно записались на платный курс.");
    }
}
