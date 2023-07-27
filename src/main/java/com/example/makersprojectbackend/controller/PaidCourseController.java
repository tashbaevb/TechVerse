package com.example.makersprojectbackend.controller;

import com.example.makersprojectbackend.models.Enroll;
import com.example.makersprojectbackend.models.PaidCourse;
import com.example.makersprojectbackend.service.EnrollService;
import com.example.makersprojectbackend.service.PaidCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paidcourses")
@RequiredArgsConstructor
public class PaidCourseController {
    private final PaidCourseService paidCourseService;
    private final EnrollService enrollService;

    @GetMapping
    public List<PaidCourse> getAllCourses() {
        return paidCourseService.getAllPaidCourses();
    }



    @PostMapping("/enrolls")
    public ResponseEntity<String> enrollPaidCourse(@RequestBody Enroll enroll) {
        enrollService.enrollPaidCourse(enroll);
        return ResponseEntity.status(HttpStatus.CREATED).body("Вы успешно записались на платный курс.");
    }

//    @PostMapping("/create")
//    public PaidCourseService
}
