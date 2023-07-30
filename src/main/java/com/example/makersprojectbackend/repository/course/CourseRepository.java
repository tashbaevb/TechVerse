package com.example.makersprojectbackend.repository.course;

import com.example.makersprojectbackend.entity.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
