package com.example.makersprojectbackend.service;

import com.example.makersprojectbackend.models.PaidCourse;

import java.util.List;

public interface PaidCourseService {
    List<PaidCourse> getAllPaidCourses();
    PaidCourse create(PaidCourse paidCourse);

}
