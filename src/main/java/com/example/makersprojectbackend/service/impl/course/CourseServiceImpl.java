package com.example.makersprojectbackend.service.impl.course;

import com.example.makersprojectbackend.entity.File;
import com.example.makersprojectbackend.entity.course.Course;
import com.example.makersprojectbackend.enums.CourseDirection;
import com.example.makersprojectbackend.enums.CourseType;
import com.example.makersprojectbackend.repository.FileRepository;
import com.example.makersprojectbackend.repository.course.CourseRepository;
import com.example.makersprojectbackend.repository.course.LectureRepository;
import com.example.makersprojectbackend.repository.course.VideoLectureRepository;
import com.example.makersprojectbackend.service.course.CourseService;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.jpa.domain.Specification;


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

        File imageData = fileRepository.save(File.builder().fileName(file.getOriginalFilename()).type(file.getContentType()).file(file.getBytes()).course(course).build());
        course.setFile(imageData);
        courseRepository.save(course);
        if (imageData.getId() != null) {
            return "file uploaded successfully: " + file.getOriginalFilename();
        }
        return null;
    }

    // SEARCH
    @Override
    public List<Course> findCoursesWithFuzzyName(String name) {
        List<Course> filteredCourses = courseRepository.findAll();

        if (!name.isEmpty()) {
            String normalizedQuery = name.toLowerCase();
            filteredCourses = filteredCourses.stream().filter(course -> course.getName().toLowerCase().contains(normalizedQuery)).collect(Collectors.toList());
        }

        return filteredCourses;
    }


    @Override
    public List<Course> getCoursesByType(CourseType courseType) {
        return courseRepository.findByCourseType(courseType);
    }


    // FILTER
    @Override
    public List<Course> filterCourses(String courseDirection, BigDecimal minPrice, BigDecimal maxPrice, Integer maxDuration) {
        Specification<Course> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (courseDirection != null) {
                predicates.add(cb.equal(root.get("courseDirection"), CourseDirection.valueOf(courseDirection)));
            }

            if (minPrice != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("price"), minPrice));
            }

            if (maxPrice != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("price"), maxPrice));
            }

            if (maxDuration != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("duration"), maxDuration));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return courseRepository.findAll(spec);
    }
}
