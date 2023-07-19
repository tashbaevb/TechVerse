package com.example.makersprojectbackend.repository;

import com.example.makersprojectbackend.models.PaidCourse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaidCourseRepository extends CrudRepository<PaidCourse, Integer> {
    List<PaidCourse> findAll();
}
