package com.makers.TechVerse.service;

import com.makers.TechVerse.models.PaidCourse;
import com.makers.TechVerse.repository.PaidCourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaidCourseServiceImpl implements PaidCourseService {
    private final PaidCourseRepository paidCourseRepository;

    public PaidCourseServiceImpl(PaidCourseRepository paidCourseRepository) {
        this.paidCourseRepository = paidCourseRepository;
    }

    @Override
    public List<PaidCourse> getAllPaidCourses(){
        return paidCourseRepository.findAll();
    }
}
