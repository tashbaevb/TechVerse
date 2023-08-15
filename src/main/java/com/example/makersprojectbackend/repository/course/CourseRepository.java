package com.example.makersprojectbackend.repository.course;

import com.example.makersprojectbackend.entity.course.Course;
import com.example.makersprojectbackend.enums.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{

    List<Course> findByCourseType(CourseType courseType);

}
