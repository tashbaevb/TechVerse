package com.example.makersprojectbackend.service.impl;

import com.example.makersprojectbackend.models.PaidCourse;
import com.example.makersprojectbackend.repository.PaidCourseRepository;
import com.example.makersprojectbackend.service.PaidCourseService;
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
