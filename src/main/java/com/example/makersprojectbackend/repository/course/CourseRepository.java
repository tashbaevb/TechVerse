package com.example.makersprojectbackend.repository.course;

import com.example.makersprojectbackend.entity.course.Course;
import com.example.makersprojectbackend.enums.CourseDirection;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
//    List<Course> findByCourseDirection(CourseDirection courseDirection);
//
//    List<Course> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
//
//    List<Course> findByDurationGreaterThanEqual(Integer minDuration);
//
//    List<Course> findByName(String name);
//
//
//    List<Course> findByCourseDirectionAndPriceBetweenAndDurationGreaterThanEqual(
//            CourseDirection direction, BigDecimal minPrice, BigDecimal maxPrice, Integer duration
//    );
//
//    List<Course> findByCourseDirectionAndPriceBetween(CourseDirection direction, BigDecimal minPrice, BigDecimal maxPrice);
//
//    List<Course> findByCourseDirectionAndDurationGreaterThanEqual(CourseDirection direction, Integer minDuration);
//
//    List<Course> findByPriceBetweenAndDurationGreaterThanEqual(BigDecimal minPrice, BigDecimal maxPrice, Integer minDuration);

    List<Course> findAll();
}
