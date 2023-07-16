package com.makers.TechVerse.repository;

import com.makers.TechVerse.models.Enroll;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollRepository extends CrudRepository<Enroll, Integer> {
    List<Enroll> findAll();
}
