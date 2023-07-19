package com.example.makersprojectbackend.service.impl;

import com.example.makersprojectbackend.models.Enroll;
import com.example.makersprojectbackend.repository.EnrollRepository;
import com.example.makersprojectbackend.service.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollServiceImpl implements EnrollService {
    private EnrollRepository enrollRepository;

    @Autowired
    public EnrollServiceImpl(EnrollRepository enrollRepository) {
        this.enrollRepository = enrollRepository;
    }

    @Override
    public List<Enroll> getAllEnrolls() {
        return enrollRepository.findAll();
    }

    @Override
    public Enroll enrollPaidCourse(Enroll enroll) {
        return enrollRepository.save(enroll);
    }
}
