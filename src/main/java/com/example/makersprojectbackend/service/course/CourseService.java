package com.example.makersprojectbackend.service.course;

import com.example.makersprojectbackend.entity.course.Course;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    String uploadImage(Long courseId, MultipartFile file) throws IOException;
}
