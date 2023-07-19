package com.example.makersprojectbackend.repository;

import com.example.makersprojectbackend.models.Enroll;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollRepository extends CrudRepository<Enroll, Integer> {
    List<Enroll> findAll();
}
