package com.example.makersprojectbackend.service;

import com.example.makersprojectbackend.entity.Course;

import java.util.List;

public interface CourseService {

    Course create(Course course);

    Course getById(Long id);

    List<Course> getAll();

    Course update(Course courseDetails);

    void delete(Long id);

    Course addLecture(Long courseId, Long lectureId);

    Course removeLecture(Long courseId, Long lectureId);

    Course addVideoLecture(Long courseId, Long lectureId);

    Course removeVideoLecture(Long courseId, Long lectureId);
}
