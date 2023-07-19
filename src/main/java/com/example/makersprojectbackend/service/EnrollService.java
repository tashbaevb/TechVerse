package com.example.makersprojectbackend.service;

import com.example.makersprojectbackend.models.Enroll;

import java.util.List;

public interface EnrollService {
    List<Enroll> getAllEnrolls();
    Enroll enrollPaidCourse(Enroll enroll);
}
