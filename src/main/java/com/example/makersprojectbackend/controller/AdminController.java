package com.example.makersprojectbackend.controller;

import com.example.makersprojectbackend.dto.CourseDto;
import com.example.makersprojectbackend.entity.Course;
import com.example.makersprojectbackend.entity.forms.Feedback;
import com.example.makersprojectbackend.entity.forms.Application;
import com.example.makersprojectbackend.mappers.CourseMapper;
import com.example.makersprojectbackend.service.FeedbackService;
import com.example.makersprojectbackend.service.ApplicationService;
import com.example.makersprojectbackend.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final CourseService courseService;
    private final CourseMapper courseMapper;
    private final ApplicationService applicationService;
    private final FeedbackService feedbackService;

    public AdminController(CourseService courseService, CourseMapper courseMapper, ApplicationService applicationService, FeedbackService feedbackService) {
        this.courseService = courseService;
        this.courseMapper = courseMapper;
        this.applicationService = applicationService;
        this.feedbackService = feedbackService;
    }


    // СПИСКИ
    @GetMapping("/get/apps")
    public List<Application> getApplications() {
        return applicationService.getAll();
    }

    @GetMapping("/download/apps")
    public ResponseEntity<byte[]> downloadApplicationsList() {
        return applicationService.exportToExcel(applicationService.getAll());
    }

    @GetMapping("/get/feedbacks")
    public List<Feedback> getFeedbacks() {
        return feedbackService.getAll();
    }

    @GetMapping("/download/feedbacks")
    public ResponseEntity<byte[]> downloadFeedbacksList() {
        return feedbackService.exportToExcel(feedbackService.getAll());
    }



    //  КУРСЫ
    @PostMapping("/course/create")
    public CourseDto createCourse(Course course) {
        return courseMapper.convertToDTO(courseService.create(course));
    }

    @GetMapping("/course/get/{id}")
    public CourseDto getCourseById(@PathVariable Long id) {
        return courseMapper.convertToDTO(courseService.getById(id));
    }

    @GetMapping("/course/get/all")
    public List<CourseDto> getAllCourses() {
        return courseMapper.convertToDTOList(courseService.getAll());
    }

    @PutMapping("/course/update")
    public CourseDto updateCourse(@RequestBody Course course) {
        return courseMapper.convertToDTO(courseService.update(course));
    }

    @DeleteMapping("/course/delete/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.delete(id);
    }

    @PutMapping("/course/lecture/add/{courseId}/{lectureId}")
    public CourseDto addLectureToCourse(@PathVariable Long courseId, @PathVariable Long lectureId) {
        return courseMapper.convertToDTO(courseService.addLecture(courseId, lectureId));
    }

    @DeleteMapping("/course/lecture/remove/{courseId}/{lectureId}")
    public CourseDto removeLectureToCourse(@PathVariable Long courseId, @PathVariable Long lectureId) {
        return courseMapper.convertToDTO(courseService.removeLecture(courseId, lectureId));
    }

    @PutMapping("/course/v/lecture/add/{courseId}/{lectureId}")
    public CourseDto addVideoLectureToCourse(@PathVariable Long courseId, @PathVariable Long lectureId) {
        return courseMapper.convertToDTO(courseService.addVideoLecture(courseId, lectureId));
    }

    @DeleteMapping("/course/v/lecture/remove/{courseId}/{lectureId}")
    public CourseDto removeVideoLectureToCourse(@PathVariable Long courseId, @PathVariable Long lectureId) {
        return courseMapper.convertToDTO(courseService.removeVideoLecture(courseId, lectureId));
    }


    //
}
