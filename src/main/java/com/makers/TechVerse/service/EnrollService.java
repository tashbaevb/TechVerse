package com.makers.TechVerse.service;

import com.makers.TechVerse.models.Enroll;
import com.makers.TechVerse.models.PaidCourse;

import java.util.List;

public interface EnrollService {
    List<Enroll> getAllEnrolls();
    Enroll enrollPaidCourse(Enroll enroll);
}
