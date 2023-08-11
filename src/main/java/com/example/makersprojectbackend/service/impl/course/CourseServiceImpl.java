package com.example.makersprojectbackend.service.impl.course;

import com.example.makersprojectbackend.entity.File;
import com.example.makersprojectbackend.entity.course.Course;
import com.example.makersprojectbackend.enums.CourseDirection;
import com.example.makersprojectbackend.repository.FileRepository;
import com.example.makersprojectbackend.repository.course.CourseRepository;
import com.example.makersprojectbackend.repository.course.LectureRepository;
import com.example.makersprojectbackend.repository.course.VideoLectureRepository;
import com.example.makersprojectbackend.service.course.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final LectureRepository lectureRepository;
    private final VideoLectureRepository videoLectureRepository;
    private final FileRepository fileRepository;

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

    @Override
    public String uploadImage(Long courseId, MultipartFile file) throws IOException {
        Course course = getById(courseId);

        File imageData = fileRepository.save(File.builder()
                .fileName(file.getOriginalFilename())
                .type(file.getContentType())
                .file(file.getBytes())
                .course(course)
                .build());

        if (imageData.getId() != null) {
            return "file uploaded successfully: " + file.getOriginalFilename();
        }
        return null;
    }


    public List<Course> filterCourses(
            CourseDirection direction, BigDecimal minPrice, BigDecimal maxPrice, Integer minDuration, String name) {

        List<Course> filteredCourses = courseRepository.findAll();

        if (direction != null) {
            filteredCourses = filteredCourses.stream()
                    .filter(course -> course.getCourseDirection() == direction)
                    .collect(Collectors.toList());
        }

        if (minPrice != null && maxPrice != null) {
            filteredCourses = filteredCourses.stream()
                    .filter(course -> course.getPrice() != null &&
                            course.getPrice().compareTo(minPrice) >= 0 &&
                            course.getPrice().compareTo(maxPrice) <= 0)
                    .collect(Collectors.toList());
        }

        if (minDuration != null) {
            filteredCourses = filteredCourses.stream()
                    .filter(course -> course.getDuration() >= minDuration)
                    .collect(Collectors.toList());
        }

        if (name != null) {
            filteredCourses = filteredCourses.stream()
                    .filter(course -> course.getName().equalsIgnoreCase(name))
                    .collect(Collectors.toList());
        }

        return filteredCourses;
    }
}
