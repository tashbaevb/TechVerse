package com.example.makersprojectbackend.service.impl;

import com.example.makersprojectbackend.entity.Course;
import com.example.makersprojectbackend.repository.CourseRepository;
import com.example.makersprojectbackend.repository.LectureRepository;
import com.example.makersprojectbackend.repository.VideoLectureRepository;
import com.example.makersprojectbackend.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final LectureRepository lectureRepository;
    private final VideoLectureRepository videoLectureRepository;

    @Override
    public Course create(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course getById(Long id) {
        return courseRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course update(Course courseDetails) {
        Course course = getById(courseDetails.getId());
        course.setName(courseDetails.getName());
        course.setDescription(courseDetails.getDescription());
        course.setDuration(course.getDuration());
        return courseRepository.save(course);
    }

    @Override
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Course addLecture(Long courseId, Long lectureId) {
        Course course = getById(courseId);
        course.getLectures().add(lectureRepository.findById(lectureId).orElseThrow());
        return courseRepository.save(course);
    }

    @Override
    public Course removeLecture(Long courseId, Long lectureId) {
        Course course = getById(courseId);
        course.getLectures().remove(lectureRepository.findById(lectureId).orElseThrow());
        return courseRepository.save(course);
    }

    @Override
    public Course addVideoLecture(Long courseId, Long videoLectureId) {
        Course course = getById(courseId);
        course.getVideoLectures().add(videoLectureRepository.findById(videoLectureId).orElseThrow());
        return courseRepository.save(course);
    }

    @Override
    public Course removeVideoLecture(Long courseId, Long videoLectureId) {
        Course course = getById(courseId);
        course.getVideoLectures().remove(videoLectureRepository.findById(videoLectureId).orElseThrow());
        return courseRepository.save(course);
    }
}
