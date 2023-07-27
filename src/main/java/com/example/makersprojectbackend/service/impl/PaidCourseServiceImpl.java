package com.example.makersprojectbackend.service.impl;

import com.example.makersprojectbackend.models.PaidCourse;
import com.example.makersprojectbackend.repository.PaidCourseRepository;
import com.example.makersprojectbackend.service.PaidCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaidCourseServiceImpl implements PaidCourseService {
    private final PaidCourseRepository paidCourseRepository;
    @Override
    public List<PaidCourse> getAllPaidCourses(){
        return paidCourseRepository.findAll();
    }

    @Override
    public PaidCourse create(PaidCourse paidCourse){
        return paidCourseRepository.save(paidCourse);
    }
}
