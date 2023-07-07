package com.example.makersprojectbackend.service;

import com.example.makersprojectbackend.entity.Course;
import com.example.makersprojectbackend.repository.CourseRepository;
import com.example.makersprojectbackend.repository.LectureRepository;
import com.example.makersprojectbackend.repository.VideoLectureRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final LectureRepository lectureRepository;
    private final VideoLectureRepository videoLectureRepository;

    public CourseService(CourseRepository courseRepository, LectureRepository lectureRepository, VideoLectureRepository videoLectureRepository) {
        this.courseRepository = courseRepository;
        this.lectureRepository = lectureRepository;
        this.videoLectureRepository = videoLectureRepository;
    }

    public Course create(Course course) {
        return courseRepository.save(course);
    }

    public Course getById(Long id) {
        return courseRepository.findById(id).orElseThrow();
    }

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public Course update(Course courseDetails) {
        Course course = getById(courseDetails.getId());
        course.setName(courseDetails.getName());
        course.setDescription(courseDetails.getDescription());
        course.setDuration(course.getDuration());
        return courseRepository.save(course);
    }

    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    public Course addLecture(Long courseId, Long lectureId) {
        Course course = getById(courseId);
        course.getLectures().add(lectureRepository.findById(lectureId).orElseThrow());
        return courseRepository.save(course);
    }

    public Course removeLecture(Long courseId, Long lectureId) {
        Course course = getById(courseId);
        course.getLectures().remove(lectureRepository.findById(lectureId).orElseThrow());
        return courseRepository.save(course);
    }

    public Course addVideoLecture(Long courseId, Long videoLectureId) {
        Course course = getById(courseId);
        course.getVideoLectures().add(videoLectureRepository.findById(videoLectureId).orElseThrow());
        return courseRepository.save(course);
    }

    public Course removeVideoLecture(Long courseId, Long videoLectureId) {
        Course course = getById(courseId);
        course.getVideoLectures().remove(videoLectureRepository.findById(videoLectureId).orElseThrow());
        return courseRepository.save(course);
    }

}
