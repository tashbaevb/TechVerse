package com.example.makersprojectbackend.service.course;

import com.example.makersprojectbackend.entity.course.Course;
import com.example.makersprojectbackend.enums.CourseType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface CourseService {

    // CRUD
    Course create(Course course);

    Course getById(Long id);

    List<Course> getAll();

    Course update(Course courseDetails);

    void delete(Long id);

    ResponseEntity<Course> addLecture(Long courseId, Long lectureId);

    Course removeLecture(Long courseId, Long lectureId) throws Exception;

    Course addVideoLecture(Long courseId, Long lectureId) throws Exception;

    Course removeVideoLecture(Long courseId, Long lectureId) throws Exception;

    String uploadImage(Long courseId, MultipartFile file) throws IOException;


    // SEARCH
    List<Course> findCoursesWithFuzzyName(String name);

    List<Course> getCoursesByType(CourseType courseType);


    // FILTER
    List<Course> filterCourses(String courseDirection, BigDecimal minPrice, BigDecimal maxPrice, Integer maxDuration);
}
